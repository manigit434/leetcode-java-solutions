class Solution {
    public int maxSubArray(int[] nums) {
        return solve(nums, 0, nums.length - 1);
    }

    private int solve(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int mid = left + (right - left) / 2;

        int leftSum = solve(nums, left, mid);
        int rightSum = solve(nums, mid + 1, right);
        int crossSum = crossSum(nums, left, mid, right);

        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    private int crossSum(int[] nums, int left, int mid, int right) {
        int sum = 0;
        int leftPart = Integer.MIN_VALUE;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            if (sum > leftPart) leftPart = sum;
        }

        sum = 0;
        int rightPart = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            if (sum > rightPart) rightPart = sum;
        }

        return leftPart + rightPart;
    }
    // Time complexity: O(n log n)
    // Space complexity: O(log n)
}
