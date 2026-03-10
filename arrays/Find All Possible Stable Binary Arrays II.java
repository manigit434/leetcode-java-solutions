class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        final int MOD = 1_000_000_007;

        long[][] dp0 = new long[zero + 1][one + 1];
        long[][] dp1 = new long[zero + 1][one + 1];

        for (int i = 0; i <= Math.min(zero, limit); ++i) {
            dp0[i][0] = 1;
        }
        for (int j = 0; j <= Math.min(one, limit); ++j) {
            dp1[0][j] = 1;
        }

        // DP transitions
        for (int i = 1; i <= zero; ++i) {
            for (int j = 1; j <= one; ++j) {
                long sub0 = (i - limit - 1 >= 0) ? dp1[i - limit - 1][j] : 0;
                dp0[i][j] = (dp0[i - 1][j] + dp1[i - 1][j] - sub0 + MOD) % MOD;

                long sub1 = (j - limit - 1 >= 0) ? dp0[i][j - limit - 1] : 0;
                dp1[i][j] = (dp0[i][j - 1] + dp1[i][j - 1] - sub1 + MOD) % MOD;
            }
        }

        return (int)((dp0[zero][one] + dp1[zero][one]) % MOD);
    }
}
