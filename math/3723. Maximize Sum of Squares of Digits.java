public class Solution {
    public String maxSumOfSquares(int num, int sumVal) {
        // If the target sum exceeds the maximum possible sum of digits, return ""
        if (sumVal > 9L * num) { 
            return "";
        }
        
        int nines = sumVal / 9;
        int remainder = sumVal % 9;
        int zeros = num - nines - (remainder > 0 ? 1 : 0);
        
        StringBuilder sb = new StringBuilder(num);
        
        // Append all 9s first
        for (int i = 0; i < nines; i++) {
            sb.append('9');
        }
        
        // Append the remainder digit if it exists
        if (remainder > 0) {
            sb.append(remainder);
        }
        
        // Fill the remaining digits with 0s
        for (int i = 0; i < zeros; i++) {
            sb.append('0');
        }
        
        return sb.toString();
    }
}
