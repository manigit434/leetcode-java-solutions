class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        int[][] countX = new int[rows + 1][cols + 1];
        int[][] countY = new int[rows + 1][cols + 1];
        int result = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int isX = (grid[i][j] == 'X') ? 1 : 0;
                int isY = (grid[i][j] == 'Y') ? 1 : 0;

 countX[i + 1][j + 1] = isX + countX[i][j + 1] + countX[i + 1][j] - countX[i][j];
countY[i + 1][j + 1] = isY + countY[i][j + 1] + countY[i + 1][j] - countY[i][j];

if (countX[i + 1][j + 1] > 0 && countX[i + 1][j + 1] == countY[i + 1][j + 1]) {
                    result++;
                }
            }
        }

        return result;
    }
}

// Time Complexity: O(rows * cols), Space Complexity: O(rows * cols)

