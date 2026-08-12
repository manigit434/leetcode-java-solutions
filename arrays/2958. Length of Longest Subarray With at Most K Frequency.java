import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        int left = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {
            int x = nums[right];
            counts.put(x, counts.getOrDefault(x, 0) + 1);

            while (counts.get(x) > k) {
                int leftVal = nums[left];
                counts.put(leftVal, counts.get(leftVal) - 1);
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
