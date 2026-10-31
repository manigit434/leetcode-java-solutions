import java.util.Arrays;

class Solution {
    private int m, n;
    private int[][] grid;
    private int[][][] memo;

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        int MOD = 1_000_000_007;

        // memo[r][c][0] stores the destination when entering (r, c) moving Right (0)
        // memo[r][c][1] stores the destination when entering (r, c) moving Down (1)
        // Values are encoded as: row * n + col, or -1 for out-of-bounds
        memo = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -2); // -2 means unvisited
            }
        }

        // dp[r][c] stores the total valid paths reaching the empty cell (r, c)
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (dp[r][c] == 0 || grid[r][c] == 1) {
                    continue;
                }

                // Option 1: Attempt to move Right from (r, c)
                int destRight = getDestination(r, c + 1, 0);
                if (destRight != -1) {
                    int nr = destRight / n;
                    int nc = destRight % n;
                    dp[nr][nc] = (dp[nr][nc] + dp[r][c]) % MOD;
                }

                // Option 2: Attempt to move Down from (r, c)
                int destDown = getDestination(r + 1, c, 1);
                if (destDown != -1) {
                    int nr = destDown / n;
                    int nc = destDown % n;
                    dp[nr][nc] = (dp[nr][nc] + dp[r][c]) % MOD;
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    // Helper to find the final landing empty cell after entering (r, c) with direction d
    private int getDestination(int r, int c, int d) {
        if (r >= m || c >= n) {
            return -1; // Out of bounds
        }
        if (grid[r][c] == 0) {
            return r * n + c; // Safely landed on an empty cell
        }
        if (memo[r][c][d] != -2) {
            return memo[r][c][d];
        }

        int result;
        if (d == 0) {
            // Entered moving Right into a mirror -> turned Down to (r + 1, c)
            result = getDestination(r + 1, c, 1);
        } else {
            // Entered moving Down into a mirror -> turned Right to (r, c + 1)
            result = getDestination(r, c + 1, 0);
        }

        memo[r][c][d] = result;
        return result;
    }
}
