import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums) {
        // Step 1: Count frequency of each number
        Map<Long, Integer> freq = new HashMap<>();
        for (int num : nums) {
            long lNum = (long) num;
            freq.put(lNum, freq.getOrDefault(lNum, 0) + 1);
        }

        int maxLength = 1;

        // Special Case: Element '1'
        // Any power of 1 is 1. If 1 exists, we can use all its occurrences.
        if (freq.containsKey(1L)) {
            int count1 = freq.get(1L);
            // The length of the pattern must be odd. 
            // If the total occurrences of 1 is even, we can only take (count1 - 1) occurrences.
            if (count1 % 2 == 0) {
                maxLength = count1 - 1;
            } else {
                maxLength = count1;
            }
        }

        // Step 2: Iterate over all unique keys to test as base 'x'
        for (long base : freq.keySet()) {
            if (base == 1L) continue;

            long currentBase = base;
            int currentLength = 0;

            // Build the increasing path (x, x^2, x^4, ...)
            while (freq.containsKey(currentBase) && freq.get(currentBase) >= 2) {
                currentLength += 2;
                currentBase = currentBase * currentBase;
            }

            // Check if the final power in the sequence exists at least once
            if (freq.containsKey(currentBase) && freq.get(currentBase) >= 1) {
                currentLength += 1;
            } else {
                // If the exact power needed doesn't exist, we must step back
                // to make sure the sequence drops symmetrically
                currentLength -= 1;
            }

            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }
}
