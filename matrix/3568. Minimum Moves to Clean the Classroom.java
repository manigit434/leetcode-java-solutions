import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length(); // Fixed length() to length
        int[][] litterId = new int[m][n];
        int startX = 0, startY = 0, totalLitter = 0;

        // Identify starting position and assign an ID to each 'L'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterId[i][j] = totalLitter++;
                }
            }
        }

        // If there is no litter, 0 moves are required
        if (totalLitter == 0) {
            return 0;
        }

        // State: visited[row][col][remaining_energy][litter_bitmask]
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << totalLitter];
        
        int initialMask = (1 << totalLitter) - 1;
        Deque<int[]> queue = new ArrayDeque<>();
        
        queue.offer(new int[]{startX, startY, energy, initialMask, 0}); // {r, c, energy, mask, steps}
        visited[startX][startY][energy][initialMask] = true;

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int e = curr[2];
            int mask = curr[3];
            int steps = curr[4];

            // If current energy is 0, the student cannot move anywhere from here
            if (e == 0) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                char nextChar = classroom[nr].charAt(nc);
                if (nextChar == 'X') continue; // Obstacle

                int ne = e - 1; // Movement costs 1 energy
                if (nextChar == 'R') {
                    ne = energy; // Reset energy to full capacity immediately upon entering
                }

                int nmask = mask;
                if (nextChar == 'L') {
                    nmask &= ~(1 << litterId[nr][nc]); // Collect litter
                }

                if (nmask == 0) {
                    return steps + 1; // All litter collected
                }

                if (!visited[nr][nc][ne][nmask]) {
                    visited[nr][nc][ne][nmask] = true;
                    queue.offer(new int[]{nr, nc, ne, nmask, steps + 1});
                }
            }
        }

        return -1; // Impossible to collect all litter
    }
}
