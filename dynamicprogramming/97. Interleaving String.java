class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();

        // Length check constraint
        if (len1 + len2 != len3) {
            return false;
        }

        // dp[j] represents whether s3[0...i+j-1] can be formed by s1[0...i-1] and s2[0...j-1]
        boolean[] dp = new boolean[len2 + 1];

        // Base case: empty s1 and empty s2 matches empty s3
        dp[0] = true;

        // Initialize the first row (when s1 is empty, only matching s2 with s3)
        for (int j = 1; j <= len2; j++) {
            dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        // Fill the DP array row by row
        for (int i = 1; i <= len1; i++) {
            // Update the 0-th column for the current row (when s2 is empty)
            dp[0] = dp[0] && s1.charAt(i - 1) == s3.charAt(i - 1);

            for (int j = 1; j <= len2; j++) {
                // Current s3 character index
                int k = i + j - 1;

                // dp[j] from the previous step represents taking from s1: (dp[j] && s1 == s3)
                // dp[j-1] from the current step represents taking from s2: (dp[j-1] && s2 == s3)
                dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(k)) || 
                        (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(k));
            }
        }

        return dp[len2];
    }
}
