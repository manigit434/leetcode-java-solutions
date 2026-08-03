public class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] represents the maximum score difference from index i to the end.
        // We only need the next 3 states, so we use a size-4 array or rolling variables.
        int[] dp = new int[4]; 
        
        // Traverse backward from the end of the stoneValue array
        for (int i = n - 1; i >= 0; i--) {
            int takeOne = stoneValue[i] - dp[(i + 1) % 4];
            
            int takeTwo = Integer.MIN_VALUE;
            if (i + 1 < n) {
                takeTwo = stoneValue[i] + stoneValue[i + 1] - dp[(i + 2) % 4];
            }
            
            int takeThree = Integer.MIN_VALUE;
            if (i + 2 < n) {
                takeThree = stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[(i + 3) % 4];
            }
            
            // Current state dp[i] is the maximum of all valid choices
            dp[i % 4] = Math.max(takeOne, Math.max(takeTwo, takeThree));
        }
        
        // dp[0] holds the final score difference (Alice - Bob) starting from index 0
        int aliceVsBob = dp[0];
        
        if (aliceVsBob > 0) {
            return "Alice";
        } else if (aliceVsBob < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
