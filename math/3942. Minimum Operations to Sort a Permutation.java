class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        
        // 1. Locate the position of 0
        int zeroIdx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIdx = i;
                break;
            }
        }
        
        // 2. Validate if it's a valid cyclic increasing permutation
        boolean isIncreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(zeroIdx + i) % n] != i) {
                isIncreasing = false;
                break;
            }
        }
        
        // 3. Validate if it's a valid cyclic decreasing permutation
        boolean isDecreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(zeroIdx - i + n) % n] != i) {
                isDecreasing = false;
                break;
            }
        }
        
        int ans = Integer.MAX_VALUE;
        
        // 4. Calculate minimal operations for both configurations
        if (isIncreasing) {
            // Strategy A: Only rotate left until 0 reaches index 0
            ans = Math.min(ans, zeroIdx);
            // Strategy B: Reverse -> Rotate left -> Reverse
            ans = Math.min(ans, n - zeroIdx + 2);
        }
        
        if (isDecreasing) {
            // Strategy C: Rotate left until 0 reaches index n-1, then reverse once
            ans = Math.min(ans, zeroIdx + 2);
            // Strategy D: Reverse once immediately, then rotate left until 0 reaches index 0
            ans = Math.min(ans, 1 + (n - 1 - zeroIdx));
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
