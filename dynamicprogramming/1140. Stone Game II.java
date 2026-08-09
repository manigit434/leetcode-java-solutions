class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // suffixSum[i] stores the total stones from index i to the end
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        // Memoization table: memo[i][m] stores max stones obtainable starting at index i with condition m
        // Since M can grow up to N, allocating [n][n + 1] is safe.
        int[][] memo = new int[n][n + 1];
        
        return helper(0, 1, suffixSum, memo);
    }
    
    private int helper(int i, int m, int[] suffixSum, int[][] memo) {
        // Base case: no piles left
        if (i >= suffixSum.length) {
            return 0;
        }
        
        // Base case: can take all remaining piles
        if (i + 2 * m >= suffixSum.length) {
            return suffixSum[i];
        }
        
        // Return cached result if already calculated
        if (memo[i][m] != 0) {
            return memo[i][m];
        }
        
        int maxStones = 0;
        
        // Try taking X piles where 1 <= X <= 2M
        for (int x = 1; x <= 2 * m; x++) {
            // Current player's stones = total remaining - what the opponent can optimally get next
            int opponentOptimal = helper(i + x, Math.max(m, x), suffixSum, memo);
            int currentPlayerStones = suffixSum[i] - opponentOptimal;
            
            maxStones = Math.max(maxStones, currentPlayerStones);
        }
        
        memo[i][m] = maxStones;
        return maxStones;
    }
}
