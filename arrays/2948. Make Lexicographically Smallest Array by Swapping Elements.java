import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Step 1: Pair each number with its original index and sort by value
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Step 2: Group elements whose consecutive sorted difference is <= limit
        int i = 0;
        while (i < n) {
            int j = i + 1;
            // Expand the group while the difference with the previous sorted element is <= limit
            while (j < n && pairs[j][0] - pairs[j - 1][0] <= limit) {
                j++;
            }
            
            // Collect and sort original indices belonging to the current group
            int[] indices = new int[j - i];
            for (int k = i; k < j; k++) {
                indices[k - i] = pairs[k][1];
            }
            Arrays.sort(indices);
            
            // Assign sorted values back into the sorted indices
            for (int k = i; k < j; k++) {
                result[indices[k - i]] = pairs[k][0];
            }
            
            // Move to the next group
            i = j;
        }
        
        return result;
    }
}
