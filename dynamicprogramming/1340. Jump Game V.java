import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        // Create an array of indices and sort them by their corresponding values in 'arr' in ascending order.
        Integer[] indices = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(indices, (a, b) -> Integer.compare(arr[a], arr[b]));
        
        int maxJumps = 0;
        
        for (int idx : indices) {
            // Check to the left
            for (int j = idx - 1; j >= Math.max(0, idx - d); j--) {
                if (arr[idx] > arr[j]) {
                    dp[idx] = Math.max(dp[idx], dp[j] + 1);
                } else {
                    break;
                }
            }
            
            // Check to the right
            for (int j = idx + 1; j <= Math.min(n - 1, idx + d); j++) {
                if (arr[idx] > arr[j]) {
                    dp[idx] = Math.max(dp[idx], dp[j] + 1);
                } else {
                    break;
                }
            }
            
            maxJumps = Math.max(maxJumps, dp[idx]);
        }
        
        return maxJumps;
    }
}

// Time Complexity: O(N * d)
// Space Complexity: O(N)
