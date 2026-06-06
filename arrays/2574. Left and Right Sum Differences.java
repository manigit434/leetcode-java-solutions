class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        
        // Calculate the total sum of all elements to represent the initial rightSum
        int rightSum = 0;
        for (int num : nums) {
            rightSum += num;
        }
        
        int leftSum = 0;
        for (int i = 0; i < n; i++) {
            // Subtract the current element from rightSum since it is no longer to the right of index i
            rightSum -= nums[i];
            
            // Calculate the absolute difference
            answer[i] = Math.abs(leftSum - rightSum);
            
            // Add the current element to leftSum for the subsequent indices
            leftSum += nums[i];
        }
        
        return answer;
    }
}
