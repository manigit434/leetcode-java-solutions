class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 1;
        
        // The LCM of any subset of numbers from 1..10 cannot exceed 2520
        int maxPossibleLcm = 2520;

        for (int i = 0; i < n; i++) {
            int prod = 1;
            int currentGcd = 0;
            int currentLcm = 1;

            for (int j = i; j < n; j++) {
                prod *= nums[j];
                
                // If prod grows beyond maxPossibleLcm * max_element, it can never equal lcm * gcd
                if (prod > maxPossibleLcm * 10) {
                    break;
                }

                currentGcd = gcd(currentGcd, nums[j]);
                currentLcm = lcm(currentLcm, nums[j]);

                if (prod == currentLcm * currentGcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}
