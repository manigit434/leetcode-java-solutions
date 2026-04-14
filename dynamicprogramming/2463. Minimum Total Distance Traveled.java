import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));

        List<Integer> factoryList = new ArrayList<>();
        for (int[] f : factory) {
            for (int i = 0; i < f[1]; i++) {
                factoryList.add(f[0]);
            }
        }

        int m = robot.size();
        int n = factoryList.size();
        long[][] dp = new long[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            dp[i][0] = (long) 1e15;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                long assign = dp[i - 1][j - 1] + Math.abs((long) robot.get(i - 1) - factoryList.get(j - 1));
                long skip = dp[i][j - 1];
                dp[i][j] = Math.min(assign, skip);
            }
        }

        return dp[m][n];
    }
}

// Time Complexity: O(N * M) where N is the total capacity of all factories and M is the number of robots.
// Space Complexity: O(N * M) to store the DP table.
