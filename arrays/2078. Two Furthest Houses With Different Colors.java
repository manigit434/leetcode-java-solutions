class Solution {
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int maxDist = 0;
        
        for (int i = 0; i < n; i++) {
            if (colors[i] != colors[n - 1]) {
                maxDist = Math.max(maxDist, n - 1 - i);
            }
            if (colors[i] != colors[0]) {
                maxDist = Math.max(maxDist, i);
            }
        }
        
        return maxDist;
    }
}
// Time Complexity: O(n) - Single pass through the array.
// Space Complexity: O(1) - Uses a constant amount of extra space.
