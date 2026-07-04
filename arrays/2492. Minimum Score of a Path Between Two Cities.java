import java.util.*;

public class Solution {
    public int minScore(int n, int[][] roads) {
        // Build the adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int weight = road[2];
            adj.get(u).add(new int[]{v, weight});
            adj.get(v).add(new int[]{u, weight});
        }
        
        // BFS initialization
        int minScore = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(1);
        visited[1] = true;
        
        // Traverse the connected component containing city 1
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int[] neighbor : adj.get(curr)) {
                int nextCity = neighbor[0];
                int weight = neighbor[1];
                
                // Track the absolute minimum edge weight seen in this component
                minScore = Math.min(minScore, weight);
                
                if (!visited[nextCity]) {
                    visited[nextCity] = true;
                    queue.offer(nextCity);
                }
            }
        }
        
        return minScore;
    }
}
