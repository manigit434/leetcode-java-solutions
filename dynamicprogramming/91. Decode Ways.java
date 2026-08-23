class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        // dp1 represents dp[i-1], dp2 represents dp[i-2]
        int dp2 = 1; // Base case for empty string
        int dp1 = 1; // Base case for string of length 1 (already verified s[0] != '0')

        for (int i = 2; i <= n; i++) {
            int current = 0;
            int singleDigit = s.charAt(i - 1) - '0';
            int doubleDigit = Integer.parseInt(s.substring(i - 2, i));

            // Check if single digit configuration is valid
            if (singleDigit >= 1 && singleDigit <= 9) {
                current += dp1;
            }

            // Check if two digit configuration is valid
            if (doubleDigit >= 10 && doubleDigit <= 26) {
                current += dp2;
            }

            // If it can't be decoded at all, early exit
            if (current == 0) {
                return 0;
            }

            // Slide the state variables forward
            dp2 = dp1;
            dp1 = current;
        }

        return dp1;
    }
}
