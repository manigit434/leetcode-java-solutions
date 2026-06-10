import java.util.*;

public class Solution {
    private int[][] stMin;
    private int[][] stMax;
    private int[] logTable;

    private static class SubarrayState {
        int l, r;
        long val;

        SubarrayState(int l, int r, long val) {
            this.l = l;
            this.r = r;
            this.val = val;
        }
    }

    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) return 0;

        // Precompute log table for O(1) range queries
        logTable = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            logTable[i] = logTable[i / 2] + 1;
        }

        int maxLog = logTable[n] + 1;
        stMin = new int[n][maxLog];
        stMax = new int[n][maxLog];

        // Initialize Sparse Table
        for (int i = 0; i < n; i++) {
            stMin[i][0] = nums[i];
            stMax[i][0] = nums[i];
        }

        // Build Sparse Table
        for (int j = 1; j < maxLog; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                stMin[i][j] = Math.min(stMin[i][j - 1], stMin[i + (1 << (j - 1))][j - 1]);
                stMax[i][j] = Math.max(stMax[i][j - 1], stMax[i + (1 << (j - 1))][j - 1]);
            }
        }

        // Max Heap tracking (-value, L, R)
        PriorityQueue<SubarrayState> maxHeap = new PriorityQueue<>((a, b) -> Long.compare(b.val, a.val));

        // Seed initial states: for each L, the maximum value is at R = n - 1
        for (int l = 0; l < n; l++) {
            long val = queryValue(l, n - 1);
            maxHeap.offer(new SubarrayState(l, n - 1, val));
        }

        long totalValue = 0;

        // Pick top k subarrays
        while (!maxHeap.isEmpty() && k > 0) {
            SubarrayState current = maxHeap.poll();
            totalValue += current.val;
            k--;

            // Move to the next best ending position for this fixed L
            if (current.r - 1 >= current.l) {
                long nextVal = queryValue(current.l, current.r - 1);
                maxHeap.offer(new SubarrayState(current.l, current.r - 1, nextVal));
            }
        }

        return totalValue;
    }

    private long queryValue(int l, int r) {
        int length = r - l + 1;
        int j = logTable[length];
        int minVal = Math.min(stMin[l][j], stMin[r - (1 << j) + 1][j]);
        int maxVal = Math.max(stMax[l][j], stMax[r - (1 << j) + 1][j]);
        return (long) maxVal - minVal;
    }
}
/*
 * Time Complexity: O(n log n + k log n) processing to build the Sparse Table and manage heap extractions.
 * Space Complexity: O(n log n) memory footprint allocation mapping the lookup grid matrices.
 */
