import java.util.List;

public class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int mod = 1000000007;

        // Use arrays to store maximum scores and total path counts
        long[][] maxScore = new long[n][n];
        int[][] pathsCount = new int[n][n];
        
        // Initialize all spaces to unreachable (-1)
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(maxScore[i], -1);
        }
        
        // Base case: Starting position 'S' at the bottom-right corner
        maxScore[n - 1][n - 1] = 0;
        pathsCount[n - 1][n - 1] = 1;
        
        // Traverse backwards from bottom-right up to top-left
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                // Skip the starting cell (already initialized) and obstacles
                if ((r == n - 1 && c == n - 1) || board.get(r).charAt(c) == 'X') {
                    continue;
                }
                
                long currentMax = -1;
                int currentPaths = 0;
                
                // Possible next steps towards the destination: Down, Right, Down-Right
                int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};
                
                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    
                    // Check boundaries and make sure the target cell is reachable
                    if (nr < n && nc < n && maxScore[nr][nc] != -1) {
                        if (maxScore[nr][nc] > currentMax) {
                            currentMax = maxScore[nr][nc];
                            currentPaths = pathsCount[nr][nc];
                        } else if (maxScore[nr][nc] == currentMax) {
                            currentPaths = (currentPaths + pathsCount[nr][nc]) % mod;
                        }
                    }
                }
                
                // If a valid path leads to this cell, calculate its value
                if (currentMax != -1) {
                    int val = (r == 0 && c == 0) ? 0 : board.get(r).charAt(c) - '0';
                    maxScore[r][c] = currentMax + val;
                    pathsCount[r][c] = currentPaths;
                }
            }
        }
        
        // If the top-left destination 'E' remains unreachable
        if (maxScore[0][0] == -1) {
            return new int[]{0, 0};
        }
        
        return new int[]{(int)(maxScore[0][0] % mod), pathsCount[0][0]};
    }
}
