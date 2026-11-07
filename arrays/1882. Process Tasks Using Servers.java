import java.util.*;

class Solution {
    class Server {
        long freeTime;
        int weight;
        int index;

        Server(long freeTime, int weight, int index) {
            this.freeTime = freeTime;
            this.weight = weight;
            this.index = index;
        }
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Min-heap for free servers: sorted by weight, then index
        PriorityQueue<Server> free = new PriorityQueue<>((a, b) -> {
            if (a.weight != b.weight) {
                return Integer.compare(a.weight, b.weight);
            }
            return Integer.compare(a.index, b.index);
        });

        // Min-heap for busy servers: sorted by freeTime, then weight, then index
        PriorityQueue<Server> used = new PriorityQueue<>((a, b) -> {
            if (a.freeTime != b.freeTime) {
                return Long.compare(a.freeTime, b.freeTime);
            }
            if (a.weight != b.weight) {
                return Integer.compare(a.weight, b.weight);
            }
            return Integer.compare(a.index, b.index);
        });

        // Initialize free servers
        for (int i = 0; i < n; i++) {
            free.offer(new Server(0, servers[i], i));
        }

        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            // Free all servers that have finished by currentTime
            while (!used.isEmpty() && used.peek().freeTime <= currentTime) {
                free.offer(used.poll());
            }

            // If no free servers are available, jump time to the next server release
            if (free.isEmpty()) {
                currentTime = used.peek().freeTime;
                while (!used.isEmpty() && used.peek().freeTime <= currentTime) {
                    free.offer(used.poll());
                }
            }

            Server server = free.poll();
            ans[j] = server.index;
            server.freeTime = currentTime + tasks[j];
            used.offer(server);
        }

        return ans;
    }
}
