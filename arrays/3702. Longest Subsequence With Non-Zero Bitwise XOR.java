class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;
        
        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        
        // If no non-zero element exists anywhere, XOR of any subsequence is 0
        if (!hasNonZero) {
            return 0;
        }
        
        // If the total XOR sum is non-zero, take the whole array
        if (totalXor != 0) {
            return nums.length;
        }
        
        // If total XOR is 0 but non-zero elements exist, remove one to make it non-zero
        return nums.length - 1;
    }
}
