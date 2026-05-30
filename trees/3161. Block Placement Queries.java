import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Solution {
    
    // Segment Tree implementation designed for Range Maximum Queries (RMQ)
    private static class SegmentTree {
        private int[] tree;
        private int n;

        public SegmentTree(int size) {
            this.n = size;
            this.tree = new int[4 * n];
        }

        public void update(int pos, int value) {
            update(0, 0, n - 1, pos, value);
        }

        private void update(int node, int start, int end, int pos, int value) {
            if (start == end) {
                tree[node] = value;
                return;
            }
            int mid = start + (end - start) / 2;
            if (pos <= mid) {
                update(2 * node + 1, start, mid, pos, value);
            } else {
                update(2 * node + 2, mid + 1, end, pos, value);
            }
            tree[node] = Math.max(tree[2 * node + 1], tree[2 * node + 2]);
        }

        public int query(int ql, int qr) {
            if (ql > qr) return 0;
            return query(0, 0, n - 1, ql, qr);
        }

        private int query(int node, int start, int end, int ql, int qr) {
            if (ql > end || qr < start) {
                return 0;
            }
            if (ql <= start && end <= qr) {
                return tree[node];
            }
            int mid = start + (end - start) / 2;
            int leftMax = query(2 * node + 1, start, mid, ql, qr);
            int rightMax = query(2 * node + 2, mid + 1, end, ql, qr);
            return Math.max(leftMax, rightMax);
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        // Calculate the coordinate upper bound up to constraints
        int maxLimit = 0;
        for (int[] q : queries) {
            maxLimit = Math.max(maxLimit, q[1]);
        }
        // Safely buffer limit bounds for indexing comfort
        int treeSize = Math.max(maxLimit, 50005) + 1;

        TreeSet<Integer> obstacles = new TreeSet<>();
        SegmentTree segTree = new SegmentTree(treeSize);
        List<Boolean> results = new ArrayList<>();

        // 0 acts as the implicit origin obstacle boundary
        obstacles.add(0);

        for (int[] q : queries) {
            if (q[0] == 1) {
                int x = q[1];
                
                int prev = obstacles.lower(x);
                Integer next = obstacles.higher(x);

                // Insert the new obstacle gap ending at x
                segTree.update(x, x - prev);

                // If an obstacle exists after x, its preceding gap shrinks
                if (next != null) {
                    segTree.update(next, next - x);
                }

                obstacles.add(x);

            } else {
                int x = q[1];
                int sz = q[2];

                // Locate the last known obstacle position before or at x
                int lastObstacle = obstacles.floor(x);
                
                // Maximum gap found completely enclosed within [0, lastObstacle]
                int maxGap = segTree.query(0, lastObstacle);

                // Check the remaining rightmost trailing gap stretching up to target point x
                maxGap = Math.max(maxGap, x - lastObstacle);

                results.add(maxGap >= sz);
            }
        }

        return results;
    }
}
