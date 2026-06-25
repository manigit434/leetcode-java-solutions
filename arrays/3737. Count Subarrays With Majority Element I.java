class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int totalValidSubarrays = 0;
        
        // Loop through all possible starting positions
        for (int i = 0; i < n; i++) {
            int balance = 0;
            
            // Expand the subarray to the right
            for (int j = i; j < n; j++) {
                if (nums[j] == target) {
                    balance++;
                } else {
                    balance--;
                }
                
                // If balance > 0, the target is the majority element
                if (balance > 0) {
                    totalValidSubarrays++;
                }
            }
        }
        
        return totalValidSubarrays;
    }
}
