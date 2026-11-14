import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] prefixSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums.get(i);
        }

        int minPositiveSum = Integer.MAX_VALUE;

        // Check every starting index and every valid length between l and r
        for (int start = 0; start < n; start++) {
            for (int len = l; len <= r && start + len <= n; len++) {
                int currentSum = prefixSum[start + len] - prefixSum[start];

                if (currentSum > 0) {
                    minPositiveSum = Math.min(minPositiveSum, currentSum);
                }
            }
        }

        return minPositiveSum == Integer.MAX_VALUE ? -1 : minPositiveSum;
    }
}
