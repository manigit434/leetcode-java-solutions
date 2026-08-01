import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Every state in the tree is a valid subset, so we add a copy to the result
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]); // Include the element
            
            backtrack(i + 1, nums, current, result); // Move to the next element
            
            current.remove(current.size() - 1); // Backtrack (exclude the element)
        }
    }
}
