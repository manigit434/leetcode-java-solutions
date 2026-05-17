import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public boolean canReach(int[] arr, int start) {
        // Time Complexity: O(N)
        // Space Complexity: O(N)
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[arr.length];
        
        queue.add(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            if (arr[current] == 0) {
                return true;
            }
            
            int forward = current + arr[current];
            if (forward < arr.length && !visited[forward]) {
                visited[forward] = true;
                queue.add(forward);
            }
            
            int backward = current - arr[current];
            if (backward >= 0 && !visited[backward]) {
                visited[backward] = true;
                queue.add(backward);
            }
        }
        
        return false;
    }
}
