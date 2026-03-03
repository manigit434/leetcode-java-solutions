/*
Problem: Regular Expression Matching
Link: https://leetcode.com/problems/regular-expression-matching/
Difficulty: Hard
Topic: Dynamic Programming / Recursion
Approach: 2D DP table tracking matches between s[0..i-1] and p[0..j-1]
Time Complexity: O(m * n)
Space Complexity: O(m * n)

Extended Description:
This solution uses dynamic programming to implement regex matching for '.' and '*' operators. 
The DP table dp[i][j] represents whether the first i characters of s match the first j characters of p. 
- '.' matches any single character.
- '*' matches zero or more of the preceding element.
We handle '*' by considering both zero occurrence (dp[i][j-2]) and one/more occurrences (dp[i-1][j] if previous char matches). 
This approach efficiently computes the match in O(m*n) time and demonstrates mastery of DP with string pattern matching, a common hard-level interview problem.
*/

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // empty string matches empty pattern

        // Initialize patterns that can match empty string (like a*, a*b*, etc.)
        for (int j = 2; j <= n; j += 2) {
            if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {
                dp[0][j] = true;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == sc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    // '*' can represent zero occurrence of previous char
                    dp[i][j] = dp[i][j - 2];
                    char prev = p.charAt(j - 2);
                    // '*' can represent one or more if previous char matches current char
                    if (prev == sc || prev == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}
