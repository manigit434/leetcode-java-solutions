import java.util.TreeSet;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (nums == null || nums.length < 2 || indexDiff <= 0 || valueDiff < 0) {
            return false;
        }

        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            long current = (long) nums[i];

            Long floor = set.floor(current);
            if (floor != null && current - floor <= valueDiff) {
                return true;
            }

            Long ceiling = set.ceiling(current);
            if (ceiling != null && ceiling - current <= valueDiff) {
                return true;
            }

            set.add(current);

            if (i >= indexDiff) {
                set.remove((long) nums[i - indexDiff]);
            }
        }

        return false;
    }
}
// Time Complexity: O(n log k) where k is indexDiff
// Space Complexity: O(k) where k is indexDiff
