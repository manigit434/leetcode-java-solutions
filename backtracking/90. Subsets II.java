import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort to bring duplicates together
        Arrays.sort(nums);
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentSubset, int[] nums, int start) {
        // Add a copy of the current valid subset to the results
        result.add(new ArrayList<>(currentSubset));

        for (int i = start; i < nums.length; i++) {
            // Skip duplicate elements at the same level of decision tree
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // Include the element
            currentSubset.add(nums[i]);
            // Move to the next element
            backtrack(result, currentSubset, nums, i + 1);
            // Backtrack: remove the element
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}
