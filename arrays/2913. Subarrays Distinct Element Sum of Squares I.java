import java.util.HashSet;
import java.util.List;

public class Solution {
    public int sumCounts(List<Integer> nums) {
        int totalSum = 0;
        int n = nums.size();
        
        // Iterate through all possible starting points of subarrays
        for (int i = 0; i < n; i++) {
            HashSet<Integer> distinctElements = new HashSet<>();
            
            // Expand the subarray from index i to j
            for (int j = i; j < n; j++) {
                distinctElements.add(nums.get(j));
                
                int distinctCount = distinctElements.size();
                totalSum += distinctCount * distinctCount;
            }
        }
        
        return totalSum;
    }
}
