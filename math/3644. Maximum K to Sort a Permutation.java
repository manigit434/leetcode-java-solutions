class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1; // Initialised to all 1-bits (-1 is 111...111 in binary)
        
        for (int i = 0; i < nums.length; ++i) {
            // Check if the current element is misplaced
            if (nums[i] != i) {
                ans &= nums[i];
            }
        }
        
        // If the array was already sorted, ans remains -1, so we return 0.
        // Otherwise, return the maximum valid k.
        return Math.max(ans, 0);
    }
}
