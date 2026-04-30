import java.util.Arrays;

class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[i][j][cost] = max score
        int[][][] dp = new int[m][n][k + 1];
        
        // Initialize with -1 to represent unreachable states
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        
        // Base case: Starting point (0,0) is always 0,0,0 per constraints
        dp[0][0][0] = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    if (dp[i][j][c] == -1) continue;
                    
                    // Try moving Right and Down
                    int[][] dirs = {{0, 1}, {1, 0}};
                    for (int[] dir : dirs) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        
                        if (ni < m && nj < n) {
                            int val = grid[ni][nj];
                            int nextCost = (val == 0) ? c : c + 1;
                            int nextScore = (val == 0) ? dp[i][j][c] : dp[i][j][c] + val;
                            
                            if (nextCost <= k) {
                                dp[ni][nj][nextCost] = Math.max(dp[ni][nj][nextCost], nextScore);
                            }
                        }
                    }
                }
            }
        }
        
        // Find the maximum score at the bottom-right corner for any cost <= k
        int maxScore = -1;
        for (int c = 0; c <= k; c++) {
            maxScore = Math.max(maxScore, dp[m - 1][n - 1][c]);
        }
        
        return maxScore;
    }
}
