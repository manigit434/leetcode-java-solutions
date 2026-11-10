class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp0 = min cost at (j - 3)
        // dp1 = min cost at (j - 2)
        // dp2 = min cost at (j - 1), initialized to step 0 with cost 0
        int dp0 = 0;
        int dp1 = 0;
        int dp2 = 0;
        
        for (int j = 1; j <= n; j++) {
            // Jump of 1 step from (j - 1): penalty is 1^2 = 1
            int bestPrev = dp2 + 1;
            
            // Jump of 2 steps from (j - 2): penalty is 2^2 = 4
            if (j >= 2) {
                bestPrev = Math.min(bestPrev, dp1 + 4);
            }
            
            // Jump of 3 steps from (j - 3): penalty is 3^2 = 9
            if (j >= 3) {
                bestPrev = Math.min(bestPrev, dp0 + 9);
            }
            
            // Add the current step's cost (costs is 0-indexed, so step j is costs[j - 1])
            int currentCost = bestPrev + costs[j - 1];
            
            // Slide the window forward for the next iteration
            dp0 = dp1;
            dp1 = dp2;
            dp2 = currentCost;
        }
        
        return dp2;
    }
}
