import java.util.Arrays;

class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        
        // Create a sorted version of the string to find target positions
        char[] sortedChars = s.toCharArray();
        Arrays.sort(sortedChars);
        
        // Case 0: Already sorted
        if (Arrays.equals(chars, sortedChars)) {
            return 0;
        }
        
        // Case -1: Length 2 and not sorted is impossible
        if (n == 2) {
            return -1;
        }
        
        // Case 1: First or last character is already in the correct position
        if (chars[0] == sortedChars[0] || chars[n - 1] == sortedChars[n - 1]) {
            return 1;
        }
        
        // Count occurrences to check if min/max are unique
        int minCount = 0;
        int maxCount = 0;
        char globalMin = sortedChars[0];
        char globalMax = sortedChars[n - 1];
        
        for (int i = 0; i < n; i++) {
            if (chars[i] == globalMin) {
                minCount++;
            }
            if (chars[i] == globalMax) {
                maxCount++;
            }
        }
        
        // Case 3: Unique max at index 0 and unique min at index n-1
        if (chars[0] == globalMax && chars[n - 1] == globalMin && minCount == 1 && maxCount == 1) {
            return 3;
        }
        
        // Case 2: All other configurations
        return 2;
    }
}
