import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int minDistance = Integer.MAX_VALUE;
        boolean found = false;

        // Group indices by their values
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }

        // Iterate through each group of indices
        for (List<Integer> indices : map.values()) {
            // We need at least 3 occurrences to form a tuple
            if (indices.size() >= 3) {
                found = true;
                // To minimize 2 * (indices[k] - indices[i]), 
                // we check consecutive triplets in the sorted list of indices
                for (int i = 0; i <= indices.size() - 3; i++) {
                    int first = indices.get(i);
                    int third = indices.get(i + 2);
                    int currentDistance = 2 * (third - first);
                    minDistance = Math.min(minDistance, currentDistance);
                }
            }
        }

        return found ? minDistance : -1;
    }
}
