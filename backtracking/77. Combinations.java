import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int n, int k, List<Integer> current, List<List<Integer>> result) {
        // Base Case: If the combination has reached the required size k
        if (current.size() == k) {
            result.add(new ArrayList<>(current)); // Create a copy of the list
            return;
        }

        // Loop from 'start' to 'n'
        // Optimization: Ensure there are enough remaining numbers to fill up to size k
        for (int i = start; i <= n - (k - current.size()) + 1; i++) {
            current.add(i); // Take the current element
            
            backtrack(i + 1, n, k, current, result); // Recurse with the next elements
            
            current.remove(current.size() - 1); // Backtrack and remove the element
        }
    }
}
