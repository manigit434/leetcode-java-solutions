import java.util.*;

public class Solution {
    
    private static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static class NodeState implements Comparable<NodeState> {
        int id;
        long totalCost;

        NodeState(int id, long totalCost) {
            this.id = id;
            this.totalCost = totalCost;
        }

        @Override
        public int compareTo(NodeState other) {
            return Long.compare(this.totalCost, other.totalCost);
        }
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // Step 1: Build the Graph structure and collect unique edge weights
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        TreeSet<Integer> uniqueCosts = new TreeSet<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            adj.get(u).add(new Edge(v, cost));
            uniqueCosts.add(cost);
        }
        
        // Convert to primitive array for quick index-based binary searching
        int[] sortedCosts = new int[uniqueCosts.size()];
        int idx = 0;
        for (int cost : uniqueCosts) {
            sortedCosts[idx++] = cost;
        }
        
        // Step 2: Binary Search over the potential maximum minimum-edge scores
        int low = 0;
        int high = sortedCosts.length - 1;
        int result = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int targetMinEdge = sortedCosts[mid];
            
            if (isValidPathPossible(n, adj, online, k, targetMinEdge)) {
                result = targetMinEdge; // Found a valid bottleneck, try to maximize it
                low = mid + 1;
            } else {
                high = mid - 1; // Path impossible, lower the bottleneck target requirement
            }
        }
        
        return result;
    }
    
    // Step 3: Dijkstra Verification routine
    private boolean isValidPathPossible(int n, List<List<Edge>> adj, boolean[] online, long k, int minEdgeRequirement) {
        long[] minPathCost = new long[n];
        Arrays.fill(minPathCost, Long.MAX_VALUE);
        minPathCost[0] = 0L;
        
        PriorityQueue<NodeState> pq = new PriorityQueue<>();
        pq.offer(new NodeState(0, 0L));
        
        while (!pq.isEmpty()) {
            NodeState curr = pq.poll();
            int u = curr.id;
            long currentCost = curr.totalCost;
            
            // Early termination if we reach the target node within cost bounds
            if (u == n - 1) {
                return currentCost <= k;
            }
            
            if (currentCost > minPathCost[u]) {
                continue;
            }
            
            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                
                // Condition A: Skip edge if it falls below the minimum required bottleneck score
                if (edge.cost < minEdgeRequirement) {
                    continue;
                }
                
                // Condition B: Skip target intermediate nodes if they are offline
                if (!online[v] && v != n - 1) {
                    continue;
                }
                
                long nextCost = currentCost + edge.cost;
                
                // Condition C: Stop expansion if path cost breaches the absolute ceiling budget k
                if (nextCost > k) {
                    continue;
                }
                
                if (nextCost < minPathCost[v]) {
                    minPathCost[v] = nextCost;
                    pq.offer(new NodeState(v, nextCost));
                }
            }
        }
        
        return minPathCost[n - 1] <= k;
    }
}
