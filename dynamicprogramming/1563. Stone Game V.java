class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        
        // prefixSums[i] stores the sum of stones from 0 to i-1
        int[] prefixSums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + stoneValue[i];
        }
        
        int[][] dp = new int[n][n];
        int[][] maxL = new int[n][n];
        int[][] maxR = new int[n][n];
        
        // Base cases: single stones have a score of 0, 
        // but maxL and maxR store (dp + sum), which equals the stone value itself.
        for (int i = 0; i < n; i++) {
            maxL[i][i] = stoneValue[i];
            maxR[i][i] = stoneValue[i];
        }
        
        // mid[i] tracks the optimal partition point for subarrays starting at index i
        int[] mid = new int[n];
        for (int i = 0; i < n; i++) {
            mid[i] = i;
        }
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                // Advance mid pointer while left sum <= right sum
                while (mid[i] < j && getSum(prefixSums, i, mid[i]) <= getSum(prefixSums, mid[i] + 1, j)) {
                    mid[i]++;
                }
                mid[i]--; // step back to the last position where left <= right
                
                // Condition check to prevent index out of bounds if mid[i] went below i
                if (mid[i] >= i) {
                    int leftSum = getSum(prefixSums, i, mid[i]);
                    int rightSum = getSum(prefixSums, mid[i] + 1, j);
                    
                    if (leftSum == rightSum) {
                        dp[i][j] = Math.max(maxL[i][mid[i]], maxR[mid[i] + 1][j]);
                    } else {
                        dp[i][j] = maxL[i][mid[i]];
                        if (mid[i] + 2 <= j) {
                            dp[i][j] = Math.max(dp[i][j], maxR[mid[i] + 2][j]);
                        }
                    }
                } else {
                    // If mid[i] < i, then left sum is always > right sum for all splits.
                    // Bob will always discard the left row, so we only take from maxR.
                    dp[i][j] = maxR[i + 1][j];
                }
                
                // Update running max matrices for larger subproblems
                int currentSum = getSum(prefixSums, i, j);
                maxL[i][j] = Math.max(maxL[i][j - 1], dp[i][j] + currentSum);
                maxR[i][j] = Math.max(maxR[i + 1][j], dp[i][j] + currentSum);
            }
        }
        
        return dp[0][n - 1];
    }
    
    private int getSum(int[] prefixSums, int i, int j) {
        if (i > j) return 0;
        return prefixSums[j + 1] - prefixSums[i];
    }
}
