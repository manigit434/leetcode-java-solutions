import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            pos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int min_dist = Integer.MAX_VALUE;
        for (List<Integer> indices : pos.values()) {
            if (indices.size() < 3) continue;
            for (int i = 0; i <= indices.size() - 3; i++) {
                int dist = 2 * (indices.get(i + 2) - indices.get(i));
                if (dist < min_dist) {
                    min_dist = dist;
                }
            }
        }

        // Time Complexity: O(N)
        // Space Complexity: O(N)
        return min_dist == Integer.MAX_VALUE ? -1 : min_dist;
    }
}
