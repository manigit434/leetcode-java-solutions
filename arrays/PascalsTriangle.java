/*
Problem: Pascal's Triangle (LeetCode #118)
Link: https://leetcode.com/problems/pascals-triangle/
Difficulty: Easy
Topic: Arrays, Dynamic Programming
Approach: Iterative row-by-row construction (Bottom-Up DP)
Time Complexity: O(n^2)
Space Complexity: O(n^2)

Extended Description:
This solution builds Pascal’s Triangle iteratively from top to bottom.
Each row is constructed using values from the previous row.

The first and last elements of every row are always 1.
For the inner elements, we use the property:
triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j]

Since every element depends only on the previous row,
we progressively build the triangle using already computed values.

We generate numRows rows, and each row contains up to n elements,
leading to O(n^2) time and space complexity.

Key Insight:
Pascal’s Triangle is a classic example of bottom-up dynamic programming,
where current results depend directly on previously computed results.
*/

import java.util.*;

class Solution {

    public List<List<Integer>> generate(int numRows) {

        // This will store the final triangle
        List<List<Integer>> triangle = new ArrayList<>();
        
        // Build each row one by one
        for (int i = 0; i < numRows; i++) {

            // Create a new row
            List<Integer> row = new ArrayList<>();
            
            // Each row has (i + 1) elements
            for (int j = 0; j <= i; j++) {

                // First and last element of every row is always 1
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    // Inner elements are sum of two elements
                    // directly above in the previous row
                    int value = triangle.get(i - 1).get(j - 1)
                              + triangle.get(i - 1).get(j);
                    row.add(value);
                }
            }
            
            // Add completed row to triangle
            triangle.add(row);
        }
        
        return triangle;
    }

    /*
     * Thought Process:
     * - Recognize the mathematical pattern of Pascal's Triangle.
     * - Each number (except edges) is derived from two numbers above it.
     * - Build row-by-row using previously computed results.
     * - This is essentially a 2D DP problem.
     */
}
