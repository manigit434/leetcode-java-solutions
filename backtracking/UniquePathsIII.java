/*
Problem: Unique Paths III
Link: https://leetcode.com/problems/unique-paths-iii/
Difficulty: Medium
Topic: Backtracking, DFS
Approach: DFS with backtracking

Time Complexity: O(4^(m*n)) worst case (all possible paths)
Space Complexity: O(m*n) recursion stack

Extended Description:
Ok so the task is basically: count all paths from start to end while walking over 
every non-obstacle square exactly once. Seems like a classic backtracking problem.  

Idea:
1. First, figure out where the start is and how many empty squares (including start and end) there are.  
2. Use DFS: try moving in all 4 directions from current square.  
3. Keep track of steps: only if we reach the end and steps == totalEmpty, count it.  
4. Mark the square as visited before diving deeper, then unmark it when coming back (backtracking).  

It's kinda tedious to imagine all possible paths, but this approach works and is standard
for "visit everything exactly once" problems. Probably not the most efficient for huge grids, 
but given constraints, it's totally fine.
*/

class Solution {
    private int rows, cols;
    private int[][] grid;
    private int totalEmpty;
    private int pathCount = 0;
    
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        int startX = 0, startY = 0;
        totalEmpty = 0;
        
        // Step 1: figure out start point and count total non-obstacles
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != -1) totalEmpty++; // count empty + start + end
                if (grid[i][j] == 1) {
                    startX = i;
                    startY = j;
                }
            }
        }
        
        // Step 2: start DFS from start square, counting start as step 1
        dfs(startX, startY, 1);
        return pathCount;
    }
    
    private void dfs(int x, int y, int steps) {
        // if outta bounds or hit obstacle, just return
        if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == -1) return;
        
        // if we reached the end
        if (grid[x][y] == 2) {
            if (steps == totalEmpty) pathCount++; // only count if all squares visited
            return;
        }
        
        // mark current square as visited so we dont revisit
        int temp = grid[x][y];
        grid[x][y] = -1;
        
        // move in 4 directions
        dfs(x + 1, y, steps + 1);
        dfs(x - 1, y, steps + 1);
        dfs(x, y + 1, steps + 1);
        dfs(x, y - 1, steps + 1);
        
        // backtrack - undo marking so other paths can use it
        grid[x][y] = temp;
    }
}
