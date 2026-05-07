// Time Complexity: O(N)
// Space Complexity: O(N)
class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int[] preMax = new int[n];
        
        preMax[0] = nums[0];
        for (int i = 1; i < n; ++i) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }
        
        int sufMin = 1 << 30;
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || preMax[i] <= sufMin) {
                ans[i] = preMax[i];
            } else {
                ans[i] = ans[i + 1];
            }
            sufMin = Math.min(sufMin, nums[i]);
        }
        
        return ans;
    }
}
