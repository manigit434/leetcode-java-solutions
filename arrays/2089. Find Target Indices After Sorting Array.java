import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        // Sort the array in non-decreasing order
        Arrays.sort(nums);
        
        List<Integer> result = new ArrayList<>();
        
        // Traverse the sorted array to find all indices matching the target
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                result.add(i);
            }
        }
        
        return result;
    }
}
