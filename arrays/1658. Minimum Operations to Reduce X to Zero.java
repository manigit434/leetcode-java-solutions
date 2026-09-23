class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // The sum required for the middle subarray
        int target = totalSum - x;

        // If the target is exactly 0, we must remove all elements
        if (target == 0) {
            return nums.length;
        }
        // If the target is negative, it's impossible to reduce x to 0
        if (target < 0) {
            return -1;
        }

        int maxLength = -1;
        int currentSum = 0;
        int left = 0;

        // Sliding window to find the longest subarray that sums to target
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            // Shrink the window from the left if the sum exceeds the target
            while (currentSum > target && left <= right) {
                currentSum -= nums[left];
                left++;
            }

            // If we find a valid subarray, track its maximum length
            if (currentSum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        // If maxLength was updated, the operations needed is total length minus maxLength
        return maxLength == -1 ? -1 : nums.length - maxLength;
    }
}
