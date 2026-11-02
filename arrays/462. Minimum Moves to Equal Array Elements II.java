import java.util.Arrays;

public class Solution {
    public int minMoves2(int[] nums) {
        // Sort the array to easily find symmetric pairs
        Arrays.sort(nums);
        
        int left = 0;
        int right = nums.length - 1;
        int moves = 0;
        
        // Accumulate differences between outer pairs
        while (left < right) {
            moves += nums[right] - nums[left];
            left++;
            right--;
        }
        
        return moves;
    }
}
