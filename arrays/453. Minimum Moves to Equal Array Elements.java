class Solution {
    public int minMoves(int[] nums) {
        // Find the minimum value in the array
        int min = nums[0];
        long sum = 0;
        
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            sum += num;
        }
        
        // Total moves = Sum of all elements - (min * length of array)
        return (int) (sum - (long) min * nums.length);
    }
}
