import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Step 1: Build the adjacency list for the directed graph
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u].add(new int[]{v, w});
        }

        // Step 2: Initialize distance table
        // dist[u][c] stores the minimum cost to reach node 'u' 
        // with 'c' consecutive identical ending characters.
        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Priority Queue stores arrays of format: {current_cost, node, consecutive_count}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // Start at node 0 with a sequence length of 1 (the character of node 0 itself)
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int cost = curr[0];
            int u = curr[1];
            int count = curr[2];

            // If we found a cheaper way to reach this state already, skip it
            if (cost > dist[u][count]) {
                continue;
            }

            // Since it's a min-heap, the first time we pop target node n - 1, it's our shortest path
            if (u == n - 1) {
                return cost;
            }

            // Explore neighbors
            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];

                // Calculate the consecutive identical count arriving at neighbor v
                int nextCount = (labels.charAt(u) == labels.charAt(v)) ? count + 1 : 1;

                // Only move to neighbor if it doesn't violate the constraint of maximum k identical characters
                if (nextCount <= k) {
                    if (cost + weight < dist[v][nextCount]) {
                        dist[v][nextCount] = cost + weight;
                        pq.offer(new int[]{dist[v][nextCount], v, nextCount});
                    }
                }
            }
        }

        return -1;
    }
}
