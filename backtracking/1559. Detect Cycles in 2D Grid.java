class Solution {
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited;
    
    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    if (dfs(grid, i, j, -1, -1, grid[i][j])) return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] grid, int r, int c, int pr, int pc, char val) {
        visited[r][c] = true;
        
        for (int[] d : dir) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] == val) {
                if (nr == pr && nc == pc) continue;
                if (visited[nr][nc]) return true;
                if (dfs(grid, nr, nc, r, c, val)) return true;
            }
        }
        return false;
    }
}
// Time Complexity: O(m * n) - Each cell is visited at most once.
// Space Complexity: O(m * n) - Visited array and recursion stack.
