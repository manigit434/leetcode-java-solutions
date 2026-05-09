import java.util.*;

class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int numLayers = Math.min(m, n) / 2;

        for (int layer = 0; layer < numLayers; layer++) {
            // 1. Extract elements of the current layer in counter-clockwise order
            List<Integer> elements = new ArrayList<>();
            
            // Top row
            for (int j = layer; j < n - layer - 1; j++) elements.add(grid[layer][j]);
            // Right column
            for (int i = layer; i < m - layer - 1; i++) elements.add(grid[i][n - layer - 1]);
            // Bottom row
            for (int j = n - layer - 1; j > layer; j--) elements.add(grid[m - layer - 1][j]);
            // Left column
            for (int i = m - layer - 1; i > layer; i--) elements.add(grid[i][layer]);

            // 2. Calculate effective rotation using modulo
            int size = elements.size();
            int netK = k % size;
            
            // 3. Re-insert elements back into the grid starting from the rotated index
            int idx = netK; 
            
            // Re-traverse using same order but pulling from 'elements' starting at 'idx'
            for (int j = layer; j < n - layer - 1; j++) grid[layer][j] = elements.get(idx++ % size);
            for (int i = layer; i < m - layer - 1; i++) grid[i][n - layer - 1] = elements.get(idx++ % size);
            for (int j = n - layer - 1; j > layer; j--) grid[m - layer - 1][j] = elements.get(idx++ % size);
            for (int i = m - layer - 1; i > layer; i--) grid[i][layer] = elements.get(idx++ % size);
        }
        return grid;
    }
}
