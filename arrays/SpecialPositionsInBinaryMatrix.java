/*
Problem: Special Positions in a Binary Matrix (LeetCode #1582)
Link: https://leetcode.com/problems/special-positions-in-a-binary-matrix/
Difficulty: Easy
Topic: Arrays, Matrix
Approach: Precompute row and column sums
Time Complexity: O(m * n)
Space Complexity: O(m + n)

Extended Description:
A position in a binary matrix is special if it contains 1 and all other elements
in its row and column are 0. 

The solution precomputes the sum of each row and each column. Then, it iterates
through the matrix and counts positions where the value is 1 and both the
corresponding row and column sums are exactly 1.

Key Insight:
- Precomputing row and column sums avoids repeated scanning of rows/columns.
- This transforms a potential O(m*n*(m+n)) brute-force approach into an
  efficient O(m*n) solution.
*/

class Solution {

    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        
        int[] rowSum = new int[m];
        int[] colSum = new int[n];
        
        // Compute sums for each row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i] += mat[i][j];
                colSum[j] += mat[i][j];
            }
        }
        
        int count = 0;
        // Identify special positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && rowSum[i] == 1 && colSum[j] == 1) {
                    count++;
                }
            }
        }
        
        return count;
    }

    /*
     * - Counting row/column sums upfront avoids repeated iteration.
     * - Special positions require strict uniqueness in both row and column.
     * - Pattern is common in matrix problems with constraints on rows and columns.
     */
}
