import java.util.*;

public class Solution {
    private static final int MOD = 1_000_000_007;
    private int[] depth;
    private int[][] up;
    private int LOG;

    // The driver method signature expects exactly two parameters
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        // Compute total nodes (n) dynamically from the edges array
        int n = edges.length + 1;

        // Space Complexity: O(N) to store the adjacency list structure for N nodes and N-1 edges
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Time Complexity: O(log N) to calculate the maximum binary lifting bits
        LOG = 0;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        // Space Complexity: O(N * log N) auxiliary space for the binary lifting table 'up'
        // Space Complexity: O(N) auxiliary space for the 'depth' tracker array
        depth = new int[n + 1];
        up = new int[n + 1][LOG];

        // Time Complexity: O(N) to traverse the tree and compute basic node parameters via DFS
        dfs(1, 1, adj);

        // Time Complexity: O(N * log N) to construct and fill the binary lifting table
        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        // Space Complexity: O(N) auxiliary space to cache the powerOfTwo lookup array
        // Time Complexity: O(N) to precompute the powers of two up to N
        int[] powerOfTwo = new int[n + 1];
        powerOfTwo[0] = 1;
        for (int i = 1; i <= n; i++) {
            powerOfTwo[i] = (powerOfTwo[i - 1] * 2) % MOD;
        }

        // Space Complexity: O(Q) memory allocation to hold the final result array for Q queries
        int[] answer = new int[queries.length];
        
        // Time Complexity: O(Q * log N) to resolve all Q queries, utilizing O(log N) LCA per query
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            if (u == v) {
                answer[i] = 0;
            } else {
                int lcaNode = getLCA(u, v);
                int d = depth[u] + depth[v] - 2 * depth[lcaNode];
                answer[i] = powerOfTwo[d - 1];
            }
        }

        // Total Time Complexity: O((N + Q) * log N)
        // Total Space Complexity: O(N * log N + Q)
        return answer;
    }

    private void dfs(int node, int parent, List<List<Integer>> adj) {
        up[node][0] = parent;
        for (int neighbor : adj.get(node)) {
            if (neighbor != parent) {
                depth[neighbor] = depth[node] + 1;
                dfs(neighbor, node, adj);
            }
        }
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Time Complexity: O(log N) to lift the deeper node to the same level as the other node
        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }

        if (u == v) {
            return u;
        }

        // Time Complexity: O(log N) to lift both nodes simultaneously to find the common ancestor
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }

        return up[u][0];
    }
}
