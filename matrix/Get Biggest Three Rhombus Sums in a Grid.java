import java.util.*;

class Solution {
    // Time: O(m * n * min(m,n)^2), Space: O(1) extra (excluding result)
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        TreeSet<Integer> set = new TreeSet<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                add(set, grid[r][c]); // size 0 rhombus

                int maxK = Math.min(Math.min(r, m - 1 - r), Math.min(c, n - 1 - c));
                for (int k = 1; k <= maxK; k++) {
                    int sum = 0;

                    int x = r - k, y = c;

                    for (int i = 0; i < k; i++) sum += grid[x + i][y + i];
                    for (int i = 0; i < k; i++) sum += grid[x + k + i][y + k - i];
                    for (int i = 0; i < k; i++) sum += grid[x + 2 * k - i][y - i];
                    for (int i = 0; i < k; i++) sum += grid[x + k - i][y - k + i];

                    add(set, sum);
                }
            }
        }

        int[] res = new int[set.size()];
        int i = res.length - 1;
        for (int v : set) res[i--] = v;
        return res;
    }

    private void add(TreeSet<Integer> set, int val) {
        set.add(val);
        if (set.size() > 3) set.pollFirst();
    }
}
