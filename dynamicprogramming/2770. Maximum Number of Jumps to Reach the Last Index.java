import java.util.Arrays;

class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (dp[i] != -1 && Math.abs(nums[j] - nums[i]) <= target) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }

        return dp[n - 1];
    }
}
// Time Complexity: O(n^2) - nested loops to check all possible pairs of indices.
// Space Complexity: O(n) - to store the dp array, where n is the length of nums.
