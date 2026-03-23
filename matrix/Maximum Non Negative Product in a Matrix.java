class Solution {
    // Time: O(m * n), Space: O(m * n)
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] maxDp = new long[m][n];
        long[][] minDp = new long[m][n];

        maxDp[0][0] = grid[0][0];
        minDp[0][0] = grid[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                long maxVal = Long.MIN_VALUE;
                long minVal = Long.MAX_VALUE;

                if (i > 0) {
                    long a = maxDp[i - 1][j] * grid[i][j];
                    long b = minDp[i - 1][j] * grid[i][j];
                    maxVal = Math.max(maxVal, Math.max(a, b));
                    minVal = Math.min(minVal, Math.min(a, b));
                }

                if (j > 0) {
                    long a = maxDp[i][j - 1] * grid[i][j];
                    long b = minDp[i][j - 1] * grid[i][j];
                    maxVal = Math.max(maxVal, Math.max(a, b));
                    minVal = Math.min(minVal, Math.min(a, b));
                }

                maxDp[i][j] = maxVal;
                minDp[i][j] = minVal;
            }
        }

        long res = maxDp[m - 1][n - 1];
        if (res < 0) return -1;
        return (int) (res % 1_000_000_007);
    }
}
