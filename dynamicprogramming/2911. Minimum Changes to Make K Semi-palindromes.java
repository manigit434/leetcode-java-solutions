import java.util.Arrays;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        // Step 1: Precompute minimum changes to make s[i..j] a semi-palindrome
        int[][] minCost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                minCost[i][j] = computeSemiPalindromeCost(s, i, j);
            }
        }

        // Step 2: DP to partition prefix of length 'i' into 'p' semi-palindromes
        int inf = 1_000_000;
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, inf);
        }
        dp[0][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            // Each part needs at least 2 characters
            for (int end = parts * 2; end <= n; end++) {
                for (int split = (parts - 1) * 2; split <= end - 2; split++) {
                    dp[parts][end] = Math.min(
                        dp[parts][end],
                        dp[parts - 1][split] + minCost[split][end - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }

    private int computeSemiPalindromeCost(String s, int start, int end) {
        int len = end - start + 1;
        int bestChanges = len;

        // Check every proper divisor d of len
        for (int d = 1; d < len; d++) {
            if (len % d != 0) {
                continue;
            }

            int changes = 0;
            // Check each of the d interleaved groups
            for (int offset = 0; offset < d; offset++) {
                int left = start + offset;
                int right = end - d + 1 + offset;

                while (left < right) {
                    if (s.charAt(left) != s.charAt(right)) {
                        changes++;
                    }
                    left += d;
                    right -= d;
                }
            }

            bestChanges = Math.min(bestChanges, changes);
        }

        return bestChanges;
    }
}
