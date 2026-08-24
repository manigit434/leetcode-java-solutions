class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: Compute prefix sums
        int[] prefixSums = new int[n];
        prefixSums[0] = stones[0];
        for (int i = 1; i < n; i++) {
            prefixSums[i] = prefixSums[i - 1] + stones[i];
        }
        
        // Step 2: Initialize DP state
        // If a player takes all stones up to the last index (n-1), 
        // the score gained is prefixSums[n-1], and no stones are left for the opponent.
        int maxDiff = prefixSums[n - 1];
        
        // Step 3: Traverse backwards from the second-to-last stone to the second stone
        for (int i = n - 2; i >= 1; i--) {
            maxDiff = Math.max(maxDiff, prefixSums[i] - maxDiff);
        }
        
        return maxDiff;
    }
}
