import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Step 1: Build the adjacency list graph
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] inv : invocations) {
            graph[inv[0]].add(inv[1]);
        }

        // Step 2: Track suspicious methods using DFS
        boolean[] isSuspicious = new boolean[n];
        dfs(k, graph, isSuspicious);

        // Step 3: Check if any non-suspicious method invokes a suspicious method
        boolean canRemove = true;
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            // If the invoker is safe but the target is suspicious, we cannot remove
            if (!isSuspicious[u] && isSuspicious[v]) {
                canRemove = false;
                break;
            }
        }

        // Step 4: Build the final list of remaining methods
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!canRemove || !isSuspicious[i]) {
                result.add(i);
            }
        }
        return result;
    }

    private void dfs(int node, List<Integer>[] graph, boolean[] isSuspicious) {
        isSuspicious[node] = true;
        for (int neighbor : graph[node]) {
            if (!isSuspicious[neighbor]) {
                dfs(neighbor, graph, isSuspicious);
            }
        }
    }
}
