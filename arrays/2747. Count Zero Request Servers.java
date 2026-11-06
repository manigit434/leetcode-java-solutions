import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] result = new int[m];
        
        // Pair each query with its original index so we can populate the result array correctly
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        
        // Sort logs and queries ascending by time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Map to keep track of active server request counts in the current window
        Map<Integer, Integer> serverCounts = new HashMap<>();
        
        int left = 0;  // Out-of-window log pointer
        int right = 0; // In-window log pointer
        
        for (int i = 0; i < m; i++) {
            int queryTime = sortedQueries[i][0];
            int originalIndex = sortedQueries[i][1];
            
            int windowStart = queryTime - x;
            int windowEnd = queryTime;
            
            // Expand the window: Add logs that fall within the current query time window
            while (right < logs.length && logs[right][1] <= windowEnd) {
                int serverId = logs[right][0];
                serverCounts.put(serverId, serverCounts.getOrDefault(serverId, 0) + 1);
                right++;
            }
            
            // Shrink the window: Remove logs that have fallen outside the window start time
            while (left < logs.length && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                if (serverCounts.containsKey(serverId)) {
                    serverCounts.put(serverId, serverCounts.get(serverId) - 1);
                    if (serverCounts.get(serverId) == 0) {
                        serverCounts.remove(serverId);
                    }
                }
                left++;
            }
            
            // Total unique active servers = serverCounts.size()
            // Unused servers = total servers (n) - active servers
            result[originalIndex] = n - serverCounts.size();
        }
        
        return result;
    }
}
