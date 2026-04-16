import java.util.*;

class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int targetIdx = queries[i];
            List<Integer> indices = map.get(nums[targetIdx]);

            if (indices == null || indices.size() <= 1) {
                result.add(-1);
                continue;
            }

            int pos = Collections.binarySearch(indices, targetIdx);
            
            int prevIdx = (pos == 0) ? indices.get(indices.size() - 1) : indices.get(pos - 1);
            int nextIdx = (pos == indices.size() - 1) ? indices.get(0) : indices.get(pos + 1);

            int dist1 = Math.min(Math.abs(targetIdx - prevIdx), n - Math.abs(targetIdx - prevIdx));
            int dist2 = Math.min(Math.abs(targetIdx - nextIdx), n - Math.abs(targetIdx - nextIdx));

            result.add(Math.min(dist1, dist2));
        }

        return result;
    }
}

/* 
Time Complexity: O(N + Q log N) 
Space Complexity: O(N) 
*/
