class Solution {
    public boolean checkValidString(String s) {
        int cmin = 0; // Minimum possible open parentheses
        int cmax = 0; // Maximum possible open parentheses
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                cmin++;
                cmax++;
            } else if (c == ')') {
                cmin = Math.max(0, cmin - 1);
                cmax--;
            } else { // c == '*'
                cmin = Math.max(0, cmin - 1); // treat '*' as ')'
                cmax++;                        // treat '*' as '('
            }
            
            if (cmax < 0) {
                return false; // More closing parentheses than opening ones
            }
        }
        
        return cmin == 0; // All open parentheses are balanced
    }
}
