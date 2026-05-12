class Solution {
    public boolean canJump(int[] nums) {
        // Time Complexity: O(n)
        // Space Complexity: O(1)
        int goal = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }
        return goal == 0;
    }
}
