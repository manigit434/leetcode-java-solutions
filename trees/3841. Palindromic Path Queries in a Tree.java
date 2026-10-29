import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] inTime;
    private int[] outTime;
    private int timer;
    private int[][] up;
    private int[] depth;
    private int LOG;
    
    // Fenwick Tree (Binary Indexed Tree) for Subtree XOR updates and Point queries
    private int[] bit;
    private int bitSize;
    
    private void updateBIT(int idx, int val) {
        for (; idx < bitSize; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }
    
    private void updateRange(int l, int r, int val) {
        updateBIT(l, val);
        updateBIT(r + 1, val);
    }
    
    private int queryBIT(int idx) {
        int sum = 0;
        for (; idx > 0; idx -= idx & -idx) {
            sum ^= bit[idx];
        }
        return sum;
    }

    // Return type changed to List<Boolean> to match LeetCode's driver requirements
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        
        inTime = new int[n];
        outTime = new int[n];
        depth = new int[n];
        timer = 1;
        
        LOG = 0;
        while ((1 << LOG) <= n) LOG++;
        up = new int[n][LOG];
        
        // Flatten the tree via DFS to establish subtree time ranges
        dfs(0, 0);
        
        bitSize = timer + 2;
        bit = new int[bitSize];
        
        // Initialize BIT by adding each node's character to its entire subtree
        char[] curChar = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int mask = 1 << (curChar[i] - 'a');
            updateRange(inTime[i], outTime[i], mask);
        }
        
        List<Boolean> result = new ArrayList<>();
        
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int node = Integer.parseInt(parts[1]);
                char newC = parts[2].charAt(0);
                
                if (curChar[node] != newC) {
                    int oldMask = 1 << (curChar[node] - 'a');
                    int newMask = 1 << (newC - 'a');
                    int change = oldMask ^ newMask;
                    
                    curChar[node] = newC;
                    // Flip the altered bitmask state across the entire subtree
                    updateRange(inTime[node], outTime[node], change);
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                
                int lcaNode = getLCA(u, v);
                
                // Fetch dynamic root-to-node path masks
                int maskU = queryBIT(inTime[u]);
                int maskV = queryBIT(inTime[v]);
                int maskLCAChar = 1 << (curChar[lcaNode] - 'a');
                
                // Path mask calculation: U XOR V XOR Character(LCA)
                int pathMask = maskU ^ maskV ^ maskLCAChar;
                
                // A path forms a palindrome if at most 1 bit is set to 1
                result.add((pathMask & (pathMask - 1)) == 0);
            }
        }
        
        return result;
    }
    
    private void dfs(int u, int p) {
        inTime[u] = timer++;
        up[u][0] = p;
        for (int j = 1; j < LOG; j++) {
            up[u][j] = up[up[u][j - 1]][j - 1];
        }
        
        for (int v : adj[u]) {
            if (v != p) {
                depth[v] = depth[u] + 1;
                dfs(v, u);
            }
        }
        outTime[u] = timer - 1;
    }
    
    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        
        for (int j = LOG - 1; j >= 0; j--) {
            if (depth[u] - (1 << j) >= depth[v]) {
                u = up[u][j];
            }
        }
        
        if (u == v) return u;
        
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }
        return up[u][0];
    }
}
