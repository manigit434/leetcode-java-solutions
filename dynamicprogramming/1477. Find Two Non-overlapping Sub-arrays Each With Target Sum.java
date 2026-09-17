import java.util.Arrays;

public class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        
        // best[i] will store the minimum length of a valid sub-array found in arr[0...i]
        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);
        
        int minSum = Integer.MAX_VALUE;
        int currentSum = 0;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            // Shrink the window from the left if the current sum exceeds the target
            while (currentSum > target) {
                currentSum -= arr[left];
                left++;
            }
            
            // If we found a valid sub-array matching the target sum
            if (currentSum == target) {
                int currentLength = right - left + 1;
                
                // Check if a valid sub-array exists to the left of our current window
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    minSum = Math.min(minSum, currentLength + best[left - 1]);
                }
                
                // Update the best historical length for the current ending index
                best[right] = Math.min(best[right], currentLength);
            }
            
            // Carry over the best minimum sub-array length found up to the previous index
            if (right > 0) {
                best[right] = Math.min(best[right], best[right - 1]);
            }
        }
        
        // Return -1 if no two non-overlapping sub-arrays were found
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}
