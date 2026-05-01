class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        long sum = 0;
        long f0 = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f0 += (long)i * nums[i];
        }
        long maxF = f0;
        long currentF = f0;
        for (int k = 1; k < n; k++) {
            currentF = currentF + sum - (long)n * nums[n - k];
            maxF = Math.max(maxF, currentF);
        }
        return (int)maxF;
    }
}
// Time Complexity: O(n) - We traverse the array to calculate sum/f0, then again in the loop.
// Space Complexity: O(1) - Only a few variables for tracking sums and maximums.
