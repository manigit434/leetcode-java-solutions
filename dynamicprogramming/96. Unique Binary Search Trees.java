class Solution {
    public int numTrees(int n) {
        // dp[i] stores the number of unique BSTs that can be formed with i nodes
        int[] dp = new int[n + 1];
        
        // Base cases
        dp[0] = 1; // An empty tree is 1 unique structural combination
        dp[1] = 1; // A tree with 1 node is 1 unique structural combination
        
        // Fill the DP table from 2 up to n
        for (int i = 2; i <= n; i++) {
            // For a tree of size i, consider each j as the root node
            for (int j = 1; j <= i; j++) {
                // Left subtree has (j - 1) nodes
                // Right subtree has (i - j) nodes
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        
        return dp[n];
    }
}
