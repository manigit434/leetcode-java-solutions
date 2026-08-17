class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[j] stores the number of subsequences matching t[0...j-1]
        int[] dp = new int[n + 1];
        
        // Base case: An empty string t can always be formed by an empty subsequence of s
        dp[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            // Iterate backwards to use values from the previous state of s
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // Include s.charAt(i-1) + Exclude s.charAt(i-1)
                    dp[j] = dp[j - 1] + dp[j];
                }
                // If characters don't match, dp[j] remains unchanged (same as excluding)
            }
        }
        
        return dp[n];
    }
}
