class Solution {
    private static final int MOD = 1_000_000_007;

    // Time: O(zero * one * limit), Space: O(zero * one)
    public int numberOfStableArrays(int zero, int one, int limit) {
        long[][] end0 = new long[zero + 1][one + 1];
        long[][] end1 = new long[zero + 1][one + 1];

        for (int z = 1; z <= Math.min(limit, zero); z++) {
            end0[z][0] = 1;
        }
        for (int o = 1; o <= Math.min(limit, one); o++) {
            end1[0][o] = 1;
        }

        for (int z = 1; z <= zero; z++) {
            for (int o = 1; o <= one; o++) {
                long val0 = 0;
                for (int k = 1; k <= Math.min(limit, z); k++) {
                    if (z - k == 0 && o == 0) continue;
                    val0 = (val0 + end1[z - k][o]) % MOD;
                }

                long val1 = 0;
                for (int k = 1; k <= Math.min(limit, o); k++) {
                    if (o - k == 0 && z == 0) continue;
                    val1 = (val1 + end0[z][o - k]) % MOD;
                }

                end0[z][o] = (end0[z][o] + val0) % MOD;
                end1[z][o] = (end1[z][o] + val1) % MOD;
            }
        }

        return (int) ((end0[zero][one] + end1[zero][one]) % MOD);
    }
}
