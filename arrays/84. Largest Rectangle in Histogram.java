import java.util.Stack;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            // Use 0 height at the end to flush out remaining bars in the stack
            int currentHeight = (i == n) ? 0 : heights[i];

            // While the current bar is shorter than the bar at stack's top
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                // If stack is empty, width is i; else it's distance between current and new top
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }
}

// Time Complexity: O(n) - Each element is pushed and popped from the stack exactly once.
// Space Complexity: O(n) - In the worst case, the stack stores all bars (e.g., increasing heights).
