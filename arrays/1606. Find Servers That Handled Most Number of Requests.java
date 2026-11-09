import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Track the count of requests handled by each server
        int[] requestCount = new int[k];
        
        // TreeSet to store currently available server IDs
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        // Min-Heap to track busy servers: stores int[]{freeTime, serverId}
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        for (int i = 0; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            int processTime = load[i];
            
            // 1. Release all servers that become free before or at the current arrival time
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= arrivalTime) {
                int[] freeServer = busyServers.poll();
                availableServers.add(freeServer[1]);
            }
            
            // If no servers are available, the request is dropped
            if (availableServers.isEmpty()) {
                continue;
            }
            
            // 2. Find the target server starting from (i % k)
            int preferredServer = i % k;
            Integer assignedServer = availableServers.ceiling(preferredServer);
            
            // If no server >= preferredServer is available, wrap around to the beginning (0)
            if (assignedServer == null) {
                assignedServer = availableServers.first();
            }
            
            // 3. Assign the request to the chosen server
            requestCount[assignedServer]++;
            availableServers.remove(assignedServer);
            busyServers.offer(new int[]{arrivalTime + processTime, assignedServer});
        }
        
        // 4. Find the maximum number of requests handled by any server
        int maxRequests = 0;
        for (int count : requestCount) {
            maxRequests = Math.max(maxRequests, count);
        }
        
        // 5. Gather all servers that match the maximum count
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}
