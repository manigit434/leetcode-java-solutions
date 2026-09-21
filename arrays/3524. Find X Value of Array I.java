class Solution {
    public long[] resultArray(int[] nums, int k) {
        // Use long[] to prevent integer overflow during accumulation
        long[] result = new long[k];
        
        // dp[r] stores the number of subarrays ending at the previous position 
        // whose product modulo k equals r.
        long[] dp = new long[k];
        
        for (int num : nums) {
            long[] nextDp = new long[k];
            int remainder = num % k;
            
            // Transition from previous subarrays ending at the prior index
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nextRemainder = (r * remainder) % k;
                    nextDp[nextRemainder] += dp[r];
                }
            }
            
            // Start a new subarray consisting solely of the current element
            nextDp[remainder]++;
            
            // Add the counts of all subarrays ending at this position to the main result
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }
            
            // Move to the next element
            dp = nextDp;
        }
        
        return result;
    }
}
