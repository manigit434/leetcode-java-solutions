class Solution {
    public int minElement(int[] nums) {
        // Initialize the minimum value to the highest possible integer
        int minSum = Integer.MAX_VALUE;
        
        // Iterate through each number in the input array
        for (int num : nums) {
            int currentDigitSum = 0;
            int temp = num;
            
            // Calculate the sum of digits for the current number
            while (temp > 0) {
                currentDigitSum += temp % 10; // Extract the last digit
                temp /= 10;                  // Remove the last digit
            }
            
            // Update the global minimum if the current digit sum is smaller
            if (currentDigitSum < minSum) {
                minSum = currentDigitSum;
            }
        }
        
        // Return the smallest digit sum found
        return minSum;
    }
}
