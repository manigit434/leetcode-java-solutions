import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Step 1: Build the adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;

        // Step 2: Iterate through all vertices
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] counts = new int[2]; // counts[0] = vertex count, counts[1] = total degrees
                dfs(i, adj, visited, counts);

                int vCount = counts[0];
                int totalDegrees = counts[1];

                // Step 4: A component is complete if totalDegrees == vCount * (vCount - 1)
                if (totalDegrees == vCount * (vCount - 1)) {
                    completeComponentsCount++;
                }
            }
        }

        return completeComponentsCount;
    }

    // Step 3: DFS traversal tracking node and edge counts
    private void dfs(int u, List<List<Integer>> adj, boolean[] visited, int[] counts) {
        visited[u] = true;
        counts[0]++; // Increment vertex count
        counts[1] += adj.get(u).size(); // Accumulate degree of current vertex

        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfs(v, adj, visited, counts);
            }
        }
    }
}
