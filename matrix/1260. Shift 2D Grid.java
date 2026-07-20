import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalSize = m * n;
        
        // Handle cases where k is larger than the total number of elements
        k = k % totalSize;
        
        // Initialize the result list
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(0);
            }
            result.add(row);
        }
        
        // Place elements in their new shifted positions
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int originalIndex = r * n + c;
                int newIndex = (originalIndex + k) % totalSize;
                
                int newRow = newIndex / n;
                int newCol = newIndex % n;
                
                result.get(newRow).set(newCol, grid[r][c]);
            }
        }
        
        return result;
    }
}
