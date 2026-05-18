import java.util.*;

public class Solution {
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        
        queue.offer(0);
        visited[0] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int idx = queue.poll();

                if (idx == n - 1) return steps;

                if (idx + 1 < n && !visited[idx + 1]) {
                    visited[idx + 1] = true;
                    queue.offer(idx + 1);
                }

                if (idx - 1 >= 0 && !visited[idx - 1]) {
                    visited[idx - 1] = true;
                    queue.offer(idx - 1);
                }

                if (graph.containsKey(arr[idx])) {
                    for (int nextIdx : graph.get(arr[idx])) {
                        if (!visited[nextIdx]) {
                            visited[nextIdx] = true;
                            queue.offer(nextIdx);
                        }
                    }
                    graph.remove(arr[idx]); 
                }
            }
            steps++;
        }

        return -1;
    }
}
