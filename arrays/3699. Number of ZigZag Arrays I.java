class Solution {
    public int zigZagArrays(int n, int l, int r) {
        long MOD = 1_000_000_007L;
        int rangeSize = r - l + 1;
        
        // up[i] = sequences ending at index i (value l + i) where the last step was an increase
        // down[i] = sequences ending at index i (value l + i) where the last step was a decrease
        long[] up = new long[rangeSize];
        long[] down = new long[rangeSize];
        
        // Base Case for length = 2:
        // A sequence of length 2 always goes up or down unless elements are equal.
        for (int i = 0; i < rangeSize; i++) {
            up[i] = i;              // There are 'i' elements smaller than index 'i'
            down[i] = rangeSize - 1 - i;  // There are 'rangeSize - 1 - i' elements larger than index 'i'
        }
        
        // DP transitions for lengths 3 up to n
        for (int len = 3; len <= n; len++) {
            long[] nextUp = new long[rangeSize];
            long[] nextDown = new long[rangeSize];
            
            // Build prefix sums to optimize transition to O(1)
            long[] prefixDown = new long[rangeSize + 1];
            long[] prefixUp = new long[rangeSize + 1];
            
            for (int i = 0; i < rangeSize; i++) {
                prefixDown[i + 1] = (prefixDown[i] + down[i]) % MOD;
                prefixUp[i + 1] = (prefixUp[i] + up[i]) % MOD;
            }
            
            // Compute counts for the current length
            for (int i = 0; i < rangeSize; i++) {
                // To step UP to 'i', the previous state must have stepped DOWN to some 'j' < 'i'
                nextUp[i] = prefixDown[i];
                
                // To step DOWN to 'i', the previous state must have stepped UP to some 'j' > 'i'
                long sumOfLargerUp = (prefixUp[rangeSize] - prefixUp[i + 1] + MOD) % MOD;
                nextDown[i] = sumOfLargerUp;
            }
            
            up = nextUp;
            down = nextDown;
        }
        
        // Gather the final answer across all possible ending values
        long totalArrays = 0;
        for (int i = 0; i < rangeSize; i++) {
            totalArrays = (totalArrays + up[i] + down[i]) % MOD;
        }
        
        return (int) totalArrays;
    }
}
