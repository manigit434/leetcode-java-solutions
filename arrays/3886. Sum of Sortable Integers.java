import java.util.*;

public class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        // Find and test all divisors of n
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (check(nums, i)) {
                    sum += i;
                }
                int counterpart = n / i;
                if (counterpart != i) {
                    if (check(nums, counterpart)) {
                        sum += counterpart;
                    }
                }
            }
        }
        
        return sum;
    }
    
    private boolean check(int[] nums, int k) {
        int n = nums.length;
        int prevMax = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i += k) {
            int currentMin = Integer.MAX_VALUE;
            int currentMax = Integer.MIN_VALUE;
            int drops = 0;
            
            // Count standard drops inside the subarray and track bounds
            for (int j = 0; j < k; j++) {
                int currIdx = i + j;
                currentMin = Math.min(currentMin, nums[currIdx]);
                currentMax = Math.max(currentMax, nums[currIdx]);
                
                int nextIdx = i + (j + 1) % k;
                if (nums[currIdx] > nums[nextIdx]) {
                    drops++;
                }
            }
            
            // A subarray must have at most 1 drop circularly to be rotatable to a sorted state
            if (drops > 1) {
                return false;
            }
            
            // Globally, the minimum of this block must be >= the maximum of the previous block
            if (currentMin < prevMax) {
                return false;
            }
            
            prevMax = currentMax;
        }
        
        return true;
    }
}
