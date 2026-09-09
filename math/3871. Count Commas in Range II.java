class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long threshold = 1000;
        
        // Loop through powers of 1000 (10^3, 10^6, 10^9, 10^12, 10^15)
        while (threshold <= n) {
            totalCommas += (n - threshold + 1);
            
            // Check to prevent overflow before multiplying
            if (threshold > Long.MAX_VALUE / 1000) {
                break;
            }
            threshold *= 1000;
        }
        
        return totalCommas;
    }
}
