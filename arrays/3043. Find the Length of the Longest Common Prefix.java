import java.util.HashSet;

public class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        HashSet<Integer> prefixes = new HashSet<>();
        
        // Time Complexity: O(N) where N is arr1.length
        // Space Complexity: O(N) to store prefixes
        for (int num : arr1) {
            while (num > 0) {
                prefixes.add(num);
                num /= 10;
            }
        }
        
        int maxLength = 0;
        
        // Time Complexity: O(K) where K is arr2.length
        // Space Complexity: O(1) auxiliary space
        for (int num : arr2) {
            while (num > 0) {
                if (prefixes.contains(num)) {
                    int currentLength = String.valueOf(num).length();
                    maxLength = Math.max(maxLength, currentLength);
                    break;
                }
                num /= 10;
            }
        }
        
        return maxLength;
    }
}
