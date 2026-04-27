import java.util.*;

class Solution {
    // Time Complexity: O(m * n) - Each cell is visited at most once using BFS.
    // Space Complexity: O(m * n) - To maintain the visited array and queue.
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];
            if (r == m - 1 && c == n - 1) return true;

            int street = grid[r][c];
            List<int[]> neighbors = getNeighbors(r, c, street, m, n);

            for (int[] next : neighbors) {
                int nr = next[0], nc = next[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                    if (canConnect(r, c, street, nr, nc, grid[nr][nc])) {
                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }
        return false;
    }

    private List<int[]> getNeighbors(int r, int c, int type, int m, int n) {
        List<int[]> neighbors = new ArrayList<>();
        if (type == 1) { neighbors.add(new int[]{r, c - 1}); neighbors.add(new int[]{r, c + 1}); }
        else if (type == 2) { neighbors.add(new int[]{r - 1, c}); neighbors.add(new int[]{r + 1, c}); }
        else if (type == 3) { neighbors.add(new int[]{r, c - 1}); neighbors.add(new int[]{r + 1, c}); }
        else if (type == 4) { neighbors.add(new int[]{r, c + 1}); neighbors.add(new int[]{r + 1, c}); }
        else if (type == 5) { neighbors.add(new int[]{r, c - 1}); neighbors.add(new int[]{r - 1, c}); }
        else if (type == 6) { neighbors.add(new int[]{r, c + 1}); neighbors.add(new int[]{r - 1, c}); }
        return neighbors;
    }

    private boolean canConnect(int r1, int c1, int t1, int r2, int c2, int t2) {
        if (c2 == c1 - 1) return (t1 == 1 || t1 == 3 || t1 == 5) && (t2 == 1 || t2 == 4 || t2 == 6);
        if (c2 == c1 + 1) return (t1 == 1 || t1 == 4 || t1 == 6) && (t2 == 1 || t2 == 3 || t2 == 5);
        if (r2 == r1 - 1) return (t1 == 2 || t1 == 5 || t1 == 6) && (t2 == 2 || t2 == 3 || t2 == 4);
        if (r2 == r1 + 1) return (t1 == 2 || t1 == 3 || t1 == 4) && (t2 == 2 || t2 == 5 || t2 == 6);
        return false;
    }
}
