class Solution {
    public int totalNumbers(int[] digits) {
        // Count the frequency of each digit available in the input array
        int[] availableCounts = new int[10];
        for (int digit : digits) {
            availableCounts[digit]++;
        }
        
        int count = 0;
        
        // Loop through all possible 3-digit even numbers
        for (int num = 100; num <= 998; num += 2) {
            int d1 = num / 100;         // Hundreds place
            int d2 = (num / 10) % 10;   // Tens place
            int d3 = num % 10;          // Ones place
            
            // Get the frequency of each digit required by the current number
            int[] requiredCounts = new int[10];
            requiredCounts[d1]++;
            requiredCounts[d2]++;
            requiredCounts[d3]++;
            
            // Check if we have enough copies of each digit
            boolean canForm = true;
            for (int i = 0; i < 10; i++) {
                if (availableCounts[i] < requiredCounts[i]) {
                    canForm = false;
                    break;
                }
            }
            
            if (canForm) {
                count++;
            }
        }
        
        return count;
    }
}
