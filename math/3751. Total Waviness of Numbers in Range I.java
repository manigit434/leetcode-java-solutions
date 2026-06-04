public class Solution {
    public int totalWaviness(int num1, int num2) {
        int totalWaviness = 0;
        
        for (int i = num1; i <= num2; i++) {
            totalWaviness += getWaviness(i);
        }
        
        return totalWaviness;
    }
    
    private int getWaviness(int num) {
        String s = Integer.toString(num);
        if (s.length() < 3) {
            return 0;
        }
        
        int count = 0;
        for (int i = 1; i < s.length() - 1; i++) {
            char curr = s.charAt(i);
            char prev = s.charAt(i - 1);
            char next = s.charAt(i + 1);
            
            if ((curr > prev && curr > next) || (curr < prev && curr < next)) {
                count++;
            }
        }
        return count;
    }
}
