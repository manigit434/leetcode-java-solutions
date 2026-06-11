import java.util.*;

public class Solution {
    public int assignEdgeWeights(int[][] edges) {
        // A tree with 'edges.length' edges always has exactly edges.length + 1 nodes
        int n = edges.length + 1;
        
        // Step 1: Create the map (adjacency list) of the tree
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Fill the graph with the given edges (it's undirected, so add both ways)
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        
        // Step 2: Use BFS (Queue) to find the maximum depth from node 1
        int maxEdges = 0;
        
        Queue<Integer> nodeQueue = new LinkedList<>();
        Queue<Integer> depthQueue = new LinkedList<>(); // Keeps track of edge count to each node
        boolean[] visited = new boolean[n + 1];
        
        // Start the search at node 1 with 0 edges traversed
        nodeQueue.offer(1);
        depthQueue.offer(0);
        visited[1] = true;
        
        while (!nodeQueue.isEmpty()) {
            int currentNode = nodeQueue.poll();
            int currentDepth = depthQueue.poll();
            
            // Keep track of the furthest depth we have seen so far
            if (currentDepth > maxEdges) {
                maxEdges = currentDepth;
            }
            
            // Check all connected neighbors of the current node
            for (int neighbor : graph.get(currentNode)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    nodeQueue.offer(neighbor);
                    depthQueue.offer(currentDepth + 1); // Neighbor is 1 edge further away
                }
            }
        }
        
        // Step 3: Compute 2^(maxEdges - 1) % 1000000007
        long MOD = 1000000007;
        long result = 1;
        int power = maxEdges - 1;
        
        // A simple loop to calculate the power safely without overflow
        for (int i = 0; i < power; i++) {
            result = (result * 2) % MOD;
        }
        
        return (int) result;
    }
}
