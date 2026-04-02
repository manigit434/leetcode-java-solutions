class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        long[][][] dp = new long[m][n][3];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Long.MIN_VALUE;
                }
            }
        }

        for (int k = 0; k < 3; k++) {
            dp[0][0][k] = coins[0][0];
            if (k > 0 && coins[0][0] < 0) {
                dp[0][0][k] = Math.max(dp[0][0][k], 0);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    if (i == 0 && j == 0) continue;

                    long fromUp = (i > 0) ? dp[i - 1][j][k] : Long.MIN_VALUE;
                    long fromLeft = (j > 0) ? dp[i][j - 1][k] : Long.MIN_VALUE;
                    long prev = Math.max(fromUp, fromLeft);

                    if (prev != Long.MIN_VALUE) {
                        dp[i][j][k] = Math.max(dp[i][j][k], prev + coins[i][j]);
                        
                        if (k > 0 && coins[i][j] < 0) {
                            long prevK = Math.max(
                                (i > 0) ? dp[i - 1][j][k - 1] : Long.MIN_VALUE,
                                (j > 0) ? dp[i][j - 1][k - 1] : Long.MIN_VALUE
                            );
                            if (prevK != Long.MIN_VALUE) {
                                dp[i][j][k] = Math.max(dp[i][j][k], prevK);
                            }
                        }
                    }
                }
            }
        }

        return (int) Math.max(dp[m - 1][n - 1][0], 
                     Math.max(dp[m - 1][n - 1][1], dp[m - 1][n - 1][2]));
    }
}
