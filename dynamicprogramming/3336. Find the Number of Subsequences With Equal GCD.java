import java.util.Arrays;

class Solution {
    int[][][] dp;
    int[] nums;
    int n;
    int MOD = 1_000_000_007;

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public int solve(int i, int g1, int g2) {
        if (i == n) {
            // Both subsequences must be non-empty (GCD > 0) and equal
            return (g1 == g2 && g1 > 0) ? 1 : 0;
        }
        if (dp[i][g1][g2] != -1) {
            return dp[i][g1][g2];
        }

        long total = 0;

        // Choice 1: Skip the current element
        total = (total + solve(i + 1, g1, g2)) % MOD;

        // Choice 2: Add to the first subsequence
        int nextG1 = (g1 == 0) ? nums[i] : gcd(g1, nums[i]);
        total = (total + solve(i + 1, nextG1, g2)) % MOD;

        // Choice 3: Add to the second subsequence
        int nextG2 = (g2 == 0) ? nums[i] : gcd(g2, nums[i]);
        total = (total + solve(i + 1, g1, nextG2)) % MOD;

        return dp[i][g1][g2] = (int) total;
    }

    public int subsequencePairCount(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        int maxVal = 200; // Constrained by problem statement nums[i] <= 200
        
        dp = new int[n][maxVal + 1][maxVal + 1];
        for (int[][] row2D : dp) {
            for (int[] row : row2D) {
                Arrays.fill(row, -1);
            }
        }

        // Start from index 0 with empty subsequences (g1 = 0, g2 = 0)
        return solve(0, 0, 0);
    }
}
