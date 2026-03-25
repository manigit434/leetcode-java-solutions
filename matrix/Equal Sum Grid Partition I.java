class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        long totalSum = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalSum += grid[i][j];
            }
        }
        if (totalSum % 2 != 0) {
            return false;
        }

        long target = totalSum / 2;

        long horizontalRunningSum = 0;
        for (int i = 0; i < m - 1; i++) { 
            for (int j = 0; j < n; j++) {
                horizontalRunningSum += grid[i][j];
            }
            if (horizontalRunningSum == target) {
                return true;
            }
        }

        long[] colSums = new long[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                colSums[j] += grid[i][j];
            }
        }

        long verticalRunningSum = 0;
        for (int j = 0; j < n - 1; j++) { 
            verticalRunningSum += colSums[j];
            if (verticalRunningSum == target) {
                return true;
            }
        }

        return false;
    }
}
