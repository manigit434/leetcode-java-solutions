import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        // Directions for moving up, down, left, right
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        // Process starting cell: if (0,0) is unsafe, reduce health immediately
        int initialHealth = health - grid.get(0).get(0);
        if (initialHealth <= 0) return false;
        
        // Queue stores int[]{row, col, remaining_health}
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, initialHealth});
        
        // visited array tracks the highest health recorded for a given cell
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = -1;
            }
        }
        visited[0][0] = initialHealth;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int h = current[2];
            
            // If we've reached the destination with positive health, return true
            if (r == m - 1 && c == n - 1 && h > 0) {
                return true;
            }
            
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                // Check grid boundaries
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextHealth = h - grid.get(nr).get(nc);
                    
                    // Only traverse if the path keeps us alive & is better than our previous visit
                    if (nextHealth > 0 && nextHealth > visited[nr][nc]) {
                        visited[nr][nc] = nextHealth;
                        queue.offer(new int[]{nr, nc, nextHealth});
                    }
                }
            }
        }
        
        return false;
    }
}
