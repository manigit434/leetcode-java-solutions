import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();
        
        for (int num : nums) {
            // Use a temporary list or string to keep digits in order for each number
            String s = Integer.toString(num);
            for (int i = 0; i < s.length(); i++) {
                list.add(s.charAt(i) - '0');
            }
        }
        
        // Convert the List back to a primitive int array
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        
        return result;
    }
}

// Time Complexity: O(n * k), where n is the number of elements and k is the average number of digits.
// Space Complexity: O(n * k) to store the result array.
