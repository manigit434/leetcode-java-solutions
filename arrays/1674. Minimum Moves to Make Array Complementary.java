class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] delta = new int[2 * limit + 2];
        
        for (int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];
            
            int min = Math.min(a, b);
            int max = Math.max(a, b);
            
            delta[2] += 2;
            delta[2 * limit + 1] -= 2;
            
            delta[min + 1] -= 1;
            delta[max + limit + 1] += 1;
            
            delta[a + b] -= 1;
            delta[a + b + 1] += 1;
        }
        
        int minMoves = n;
        int currentMoves = 0;
        
        for (int i = 2; i <= 2 * limit; i++) {
            currentMoves += delta[i];
            if (currentMoves < minMoves) {
                minMoves = currentMoves;
            }
        }
        
        return minMoves;
    }
}
/*
 * Time Complexity: O(N + K), where N is the length of nums and K is the limit.
 * Space Complexity: O(K), required for the delta difference array.
 */
