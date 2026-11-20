import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        
        // dp.get(v) stores the max consecutive sequence length ending at value v
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 1;
        
        for (int num : nums) {
            // Update (num + 1) first so we don't overwrite dp[num] before reading it
            dp.put(num + 1, dp.getOrDefault(num, 0) + 1);
            dp.put(num, dp.getOrDefault(num - 1, 0) + 1);
            
            maxLen = Math.max(maxLen, Math.max(dp.get(num), dp.get(num + 1)));
        }
        
        return maxLen;
    }
}
