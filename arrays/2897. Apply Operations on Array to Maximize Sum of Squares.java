import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long MOD = 1_000_000_007L;
        // Since nums[i] <= 10^9, we need up to 31 bits
        int[] bitCounts = new int[31];
        
        // Count the total number of set bits at each position
        for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }
        
        long totalSumOfSquares = 0;
        
        // Form the k largest possible numbers greedily
        for (int step = 0; step < k; step++) {
            long currentNum = 0;
            for (int i = 0; i < 31; i++) {
                if (bitCounts[i] > 0) {
                    currentNum |= (1L << i);
                    bitCounts[i]--; // Consume this bit for the current largest number
                }
            }
            // Add the square of the formed number to the result modulo 10^9 + 7
            long square = (currentNum * currentNum) % MOD;
            totalSumOfSquares = (totalSumOfSquares + square) % MOD;
        }
        
        return (int) totalSumOfSquares;
    }
}
