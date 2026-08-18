import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        // Map to store how many subarrays of size k contain each number
        Map<Integer, Integer> subarrayCount = new HashMap<>();

        // Slide a window of size k across the array
        for (int i = 0; i <= n - k; i++) {
            // Use a Set to avoid counting duplicate elements within the *same* subarray
            Set<Integer> uniqueInSubarray = new HashSet<>();
            for (int j = i; j < i + k; j++) {
                uniqueInSubarray.add(nums[j]);
            }
            
            // Increment the subarray visibility count for each unique number found
            for (int num : uniqueInSubarray) {
                subarrayCount.put(num, subarrayCount.getOrDefault(num, 0) + 1);
            }
        }

        int maxAlmostMissing = -1;

        // Find the largest number that appeared in exactly 1 subarray
        for (Map.Entry<Integer, Integer> entry : subarrayCount.entrySet()) {
            if (entry.getValue() == 1) {
                maxAlmostMissing = Math.max(maxAlmostMissing, entry.getKey());
            }
        }

        return maxAlmostMissing;
    }
}
