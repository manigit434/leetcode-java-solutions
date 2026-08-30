class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 1;
        }

        // Step 1: Find the indices of the minimum and maximum elements
        int minIdx = 0;
        int maxIdx = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        // Step 2: Ensure minIdx is always the smaller index for easier math
        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);

        // Step 3: Calculate deletions for the 3 available strategies
        int removeFromFront = j + 1;
        int removeFromBack = n - i;
        int removeFromBoth = (i + 1) + (n - j);

        // Step 4: Return the minimum of all three options
        return Math.min(removeFromFront, Math.min(removeFromBack, removeFromBoth));
    }
}
