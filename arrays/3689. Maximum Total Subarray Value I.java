class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        
        for (int num : nums) {
            if (num > maxVal) maxVal = num;
            if (num < minVal) minVal = num;
        }
        
        // Cast to long to prevent integer overflow with large inputs
        return (long) (maxVal - minVal) * k;
    }
}
