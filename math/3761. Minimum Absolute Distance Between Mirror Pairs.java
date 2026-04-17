import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> targets = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;

        for (int j = 0; j < nums.length; j++) {
            // Check if current number nums[j] matches any reverse(nums[i]) seen before
            if (targets.containsKey(nums[j])) {
                minDistance = Math.min(minDistance, j - targets.get(nums[j]));
            }
            
            // Store the reversal of the current number as a future target
            // If the same reversal exists, we keep the latest index to minimize distance
            targets.put(reverse(nums[j]), j);
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private int reverse(int n) {
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + (n % 10);
            n /= 10;
        }
        return rev;
    }
}

// Time Complexity: O(n * log10(max(nums[i])))
// Space Complexity: O(n)
