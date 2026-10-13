import java.util.Arrays;

public class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        // Step 1: Square all the elements
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        
        // Step 2: Sort the squares in ascending order
        Arrays.sort(squares);
        
        long maxScore = 0;
        int left = 0;
        int right = n - 1;
        
        // Step 3: Alternately add the largest and subtract the smallest
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                // Even index: Add the largest remaining square
                maxScore += squares[right];
                right--;
            } else {
                // Odd index: Subtract the smallest remaining square
                maxScore -= squares[left];
                left++;
            }
        }
        
        return maxScore;
    }
}
