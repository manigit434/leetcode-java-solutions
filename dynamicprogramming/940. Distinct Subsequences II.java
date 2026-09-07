class Solution {
    public int distinctSubseqII(String s) {
        int mod = 1_000_000_007;
        long[] dp = new long[26];
        long total = 0;

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            long newSub = (total - dp[idx] + 1 + mod) % mod;
            dp[idx] = (dp[idx] + newSub) % mod;
            total = (total + newSub) % mod;
        }

        return (int) total;
    }
}
