import java.util.Arrays;

public class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        // Find the maximum possible difference to size our bucket array
        int maxDiff = 0;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            if (diffs[i] > maxDiff) {
                maxDiff = diffs[i];
            }
        }
        
        // If maxDiff is 0, the sum of squared differences is already 0
        if (maxDiff == 0) return 0;
        
        // Bucket array where count[d] stores the frequency of difference 'd'
        int[] count = new int[maxDiff + 1];
        for (int diff : diffs) {
            count[diff]++;
        }
        
        // Greedily reduce the largest differences down to smaller differences
        for (int d = maxDiff; d > 0; d--) {
            if (count[d] == 0) continue;
            
            // Total operations needed to reduce all current differences of size 'd' by 1
            long needed = count[d];
            
            if (k >= needed) {
                // We have enough budget to reduce all elements of size 'd' to 'd - 1'
                count[d - 1] += count[d];
                k -= needed;
                count[d] = 0;
            } else {
                // We can only reduce some of the elements of size 'd'
                count[d - 1] += (int) k;
                count[d] -= (int) k;
                k = 0;
                break; // Budget exhausted
            }
        }
        
        // Calculate the final sum of squared differences
        long minSquaredSum = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (count[d] > 0) {
                minSquaredSum += (long) count[d] * d * d;
            }
        }
        
        return minSquaredSum;
    }
}
