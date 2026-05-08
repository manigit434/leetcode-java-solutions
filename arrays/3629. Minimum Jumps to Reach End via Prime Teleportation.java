import java.util.*;

class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);

        boolean[] isPrime = new boolean[maxVal + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int p = 2; p * p <= maxVal; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= maxVal; i += p)
                    isPrime[i] = false;
            }
        }

        List<Integer>[] valToIndices = new List[maxVal + 1];
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            if (valToIndices[v] == null) valToIndices[v] = new ArrayList<>();
            valToIndices[v].add(i);
        }

        List<Integer>[] primeToIndices = new List[maxVal + 1];
        for (int p = 2; p <= maxVal; p++) {
            if (isPrime[p]) {
                primeToIndices[p] = new ArrayList<>();
                for (int multiple = p; multiple <= maxVal; multiple += p) {
                    if (valToIndices[multiple] != null) {
                        primeToIndices[p].addAll(valToIndices[multiple]);
                    }
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 0;

        boolean[] usedPrime = new boolean[maxVal + 1];

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr == n - 1) return dist[curr];

            if (curr + 1 < n && dist[curr + 1] == -1) {
                dist[curr + 1] = dist[curr] + 1;
                queue.offer(curr + 1);
            }
            if (curr - 1 >= 0 && dist[curr - 1] == -1) {
                dist[curr - 1] = dist[curr] + 1;
                queue.offer(curr - 1);
            }

            int val = nums[curr];
            if (val <= maxVal && isPrime[val] && !usedPrime[val]) {
                usedPrime[val] = true;
                for (int nextIdx : primeToIndices[val]) {
                    if (dist[nextIdx] == -1) {
                        dist[nextIdx] = dist[curr] + 1;
                        queue.offer(nextIdx);
                    }
                }
            }
        }

        return dist[n - 1];
    }
}

// Time Complexity: O(V log log V + N + Σ (multiples of primes present in nums))
// Space Complexity: O(V + N)
