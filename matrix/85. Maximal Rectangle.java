import java.util.Stack;

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;
        
        // Process row by row to build histograms
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < cols; j++) {
                // If '1', increase current height; otherwise, reset to 0
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            // Find the maximum rectangle for the current row's histogram
            maxArea = Math.max(maxArea, leaseRectangleArea(heights));
        }
        
        return maxArea;
    }
    
    // Helper method to find largest rectangle in a histogram using a Monotonic Stack
    private int leaseRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;
        
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        
        return maxArea;
    }
}

// Time Complexity: O(R * C)
// Where R is the number of rows and C is the number of columns. Each cell is visited a constant number of times.

// Space Complexity: O(C)
// The memory consumed is for the heights array and the monotonic stack, both proportional to the number of columns.
