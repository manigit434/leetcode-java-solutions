class Solution {
    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Step 1: Eliminate all land (0) connected to the boundaries
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == 0 || r == rows - 1 || c == 0 || c == cols - 1) {
                    if (grid[r][c] == 0) {
                        dfs(grid, r, c);
                    }
                }
            }
        }
        
        // Step 2: Count the remaining closed islands in the interior
        int closedIslands = 0;
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                if (grid[r][c] == 0) {
                    closedIslands++;
                    dfs(grid, r, c);
                }
            }
        }
        
        return closedIslands;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        // Check out of bounds or if it's water (1)
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 1) {
            return;
        }
        
        // Mark the land cell as visited/water (1)
        grid[r][c] = 1;
        
        // Explore 4 cardinal directions: up, down, left, right
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
