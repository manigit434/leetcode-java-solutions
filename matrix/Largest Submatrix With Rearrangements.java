import java.util.*;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }

        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            int[] heights = matrix[i].clone();
            Arrays.sort(heights);

            for (int j = 0; j < n; j++) {
                int h = heights[n - 1 - j]; // descending
                maxArea = Math.max(maxArea, h * (j + 1));
            }
        }

        return maxArea;
    }
}

// Time: O(m * n log n)
// Space: O(n)
