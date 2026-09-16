import java.util.*;

public class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length / 2;
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // Left half lists: leftSums[k] stores all subset sums of size k from the left half
        List<Integer>[] leftSums = new List[n + 1];
        List<Integer>[] rightSums = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            leftSums[i] = new ArrayList<>();
            rightSums[i] = new ArrayList<>();
        }

        // Generate subset sums for both halves using bitmasking
        for (int mask = 0; mask < (1 << n); mask++) {
            int leftSum = 0;
            int rightSum = 0;
            int count = Integer.bitCount(mask);

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    leftSum += nums[i];
                    rightSum += nums[i + n];
                }
            }
            leftSums[count].add(leftSum);
            rightSums[count].add(rightSum);
        }

        // Sort the right half lists to enable binary search
        for (int i = 0; i <= n; i++) {
            Collections.sort(rightSums[i]);
        }

        int minDiff = Integer.MAX_VALUE;
        int target = totalSum / 2;

        // Iterate through all sizes k for the left half
        for (int k = 0; k <= n; k++) {
            List<Integer> leftList = leftSums[k];
            List<Integer> rightList = rightSums[n - k]; // We need n - k elements from the right half

            for (int a : leftList) {
                // We want a + b to be close to totalSum / 2 -> b close to (totalSum / 2) - a
                int expectedB = target - a;
                
                // Binary search for the best b matching expectedB
                int low = 0, high = rightList.size() - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    int b = rightList.get(mid);
                    int currentSum = a + b;
                    
                    minDiff = Math.min(minDiff, Math.abs(totalSum - 2 * currentSum));

                    if (currentSum < target) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            }
        }

        return minDiff;
    }
}
