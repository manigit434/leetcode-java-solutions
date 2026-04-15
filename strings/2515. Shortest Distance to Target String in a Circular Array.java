class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                int directDist = Math.abs(i - startIndex);
                int circularDist = n - directDist;
                minDistance = Math.min(minDistance, Math.min(directDist, circularDist));
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}
// Time Complexity: O(N)
// Space Complexity: O(1)
