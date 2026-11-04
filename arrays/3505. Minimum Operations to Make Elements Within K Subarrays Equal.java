import java.util.*;

public class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        
        // cost[i] will store the minimum operations to equalize the window starting at index i
        long[] cost = new long[n - x + 1];
        
        // Two TreeMaps to handle sliding window median and sums
        TreeMap<Integer, Integer> left = new TreeMap<>();  // Max-Heap (stores smaller half)
        TreeMap<Integer, Integer> right = new TreeMap<>(); // Min-Heap (stores larger half)
        int leftCount = 0, rightCount = 0;
        long leftSum = 0, rightSum = 0;
        
        for (int i = 0; i < n; i++) {
            // Add current element into the heaps
            if (left.isEmpty() || nums[i] <= left.lastKey()) {
                left.put(nums[i], left.getOrDefault(nums[i], 0) + 1);
                leftSum += nums[i];
                leftCount++;
            } else {
                right.put(nums[i], right.getOrDefault(nums[i], 0) + 1);
                rightSum += nums[i];
                rightCount++;
            }
            
            // Remove outgoing element if window size exceeds x
            if (i >= x) {
                int out = nums[i - x];
                if (left.containsKey(out)) {
                    int c = left.get(out);
                    if (c == 1) left.remove(out);
                    else left.put(out, c - 1);
                    leftSum -= out;
                    leftCount--;
                } else {
                    int c = right.get(out);
                    if (c == 1) right.remove(out);
                    else right.put(out, c - 1);
                    rightSum -= out;
                    rightCount--;
                }
            }
            
            // Rebalance heaps so that leftCount == rightCount or leftCount == rightCount + 1
            while (leftCount < rightCount) {
                int firstRight = right.firstKey();
                int c = right.get(firstRight);
                if (c == 1) right.remove(firstRight);
                else right.put(firstRight, c - 1);
                rightSum -= firstRight;
                rightCount--;
                
                left.put(firstRight, left.getOrDefault(firstRight, 0) + 1);
                leftSum += firstRight;
                leftCount++;
            }
            
            while (leftCount > rightCount + 1) {
                int lastLeft = left.lastKey();
                int c = left.get(lastLeft);
                if (c == 1) left.remove(lastLeft);
                else left.put(lastLeft, c - 1);
                leftSum -= lastLeft;
                leftCount--;
                
                right.put(lastLeft, right.getOrDefault(lastLeft, 0) + 1);
                rightSum += lastLeft;
                rightCount++;
            }
            
            // If we have a valid window of size x, calculate its L1 optimization cost
            if (i >= x - 1) {
                long median = left.lastKey();
                long currentCost = (median * leftCount - leftSum) + (rightSum - median * rightCount);
                cost[i - x + 1] = currentCost;
            }
        }
        
        // dp[i][j] represents the minimum cost to pick j non-overlapping windows from suffix nums[i...n-1]
        long INF = (long) 1e16;
        long[][] dp = new long[n + 1][k + 1];
        
        // Base Cases
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0; // 0 subarrays always cost 0
        }
        
        // Process backward from the end of the array to enforce non-overlapping sequence
        for (int i = n - x; i >= 0; i--) {
            for (int j = 1; j <= k; j++) {
                // Option 1: Do not start a window at index i (skip index i)
                long ans = dp[i + 1][j];
                
                // Option 2: Choose a window starting at index i
                if (i + x <= n) {
                    ans = Math.min(ans, cost[i] + dp[i + x][j - 1]);
                }
                
                dp[i][j] = ans;
            }
        }
        
        return dp[0][k];
    }
}
