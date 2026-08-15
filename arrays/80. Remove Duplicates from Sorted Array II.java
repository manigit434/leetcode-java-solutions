class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        
        int k = 2; // Write pointer starting at index 2
        
        for (int i = 2; i < nums.length; i++) {
            // Check if current element is different from the element 
            // two spots before the current write position
            if (nums[i] != nums[k - 2]) {
                nums[k] = nums[i];
                k++;
            }
        }
        
        return k;
    }
}
