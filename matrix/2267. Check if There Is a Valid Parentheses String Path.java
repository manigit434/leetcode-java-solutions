class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // A valid parentheses string must have an even length
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        
        // Maximum possible balance cannot exceed the total steps in the matrix
        int maxBal = m + n;
        boolean[][][] memo = new boolean[m][n][maxBal];
        
        return dfs(grid, 0, 0, 0, m, n, memo);
    }
    
    private boolean dfs(char[][] grid, int r, int c, int bal, int m, int n, boolean[][][] memo) {
        // Update balance based on current cell
        if (grid[r][c] == '(') {
            bal++;
        } else {
            bal--;
        }
        
        // Invalid if balance drops below 0 
        // Or if balance exceeds remaining possible steps to reach the end
        if (bal < 0 || bal > (m - r + n - c)) {
            return false;
        }
        
        // Reached destination
        if (r == m - 1 && c == n - 1) {
            return bal == 0;
        }
        
        // Return false if this state has already been visited and processed
        if (memo[r][c][bal]) {
            return false;
        }
        
        // Mark current state as visited
        memo[r][c][bal] = true;
        
        // Move Right
        if (c + 1 < n && dfs(grid, r, c + 1, bal, m, n, memo)) {
            return true;
        }
        
        // Move Down
        if (r + 1 < m && dfs(grid, r + 1, c, bal, m, n, memo)) {
            return true;
        }
        
        return false;
    }
}
