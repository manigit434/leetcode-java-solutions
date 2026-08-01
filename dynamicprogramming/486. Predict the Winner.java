public class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        // dp[i][j] stores the max relative score difference the current player can get from nums[i...j]
        int[][] dp = new int[n][n];
        
        // Base case: when there's only one element, the player must pick it
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        
        // Build the table for subarrays of length 2 up to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        
        // If the max relative score difference for the whole array is >= 0, Player 1 wins
        return dp[0][n - 1] >= 0;
    }
}
