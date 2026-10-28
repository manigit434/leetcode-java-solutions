public class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[i][j][k] is true if an XOR sum of 'k' is possible at cell (i, j)
        // Since m * n <= 1000 and max value is 1023, 1024 states are sufficient.
        boolean[][][] dp = new boolean[m][n][1024];
        
        // Initialize the starting cell with its own value
        dp[0][0][grid[0][0]] = true;
        
        // Traverse the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 1024; k++) {
                    if (dp[i][j][k]) {
                        // Move Right
                        if (j + 1 < n) {
                            dp[i][j + 1][k ^ grid[i][j + 1]] = true;
                        }
                        // Move Down
                        if (i + 1 < m) {
                            dp[i + 1][j][k ^ grid[i + 1][j]] = true;
                        }
                    }
                }
            }
        }
        
        // Find the smallest possible XOR sum at the destination cell (m-1, n-1)
        for (int k = 0; k < 1024; k++) {
            if (dp[m - 1][n - 1][k]) {
                return k;
            }
        }
        
        return 0;
    }
}
