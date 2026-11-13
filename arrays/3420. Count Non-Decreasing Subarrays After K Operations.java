import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long validSubarrays = 0;
        long currentCost = 0;

        // Monotonic decreasing deque storing indices of running maximums from left to right
        Deque<Integer> deque = new ArrayDeque<>();
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            // Adding nums[left] at the front lifts all smaller blocks to its right
            while (!deque.isEmpty() && nums[deque.peekFirst()] <= nums[left]) {
                int poppedIdx = deque.pollFirst();
                int nextBoundary = deque.isEmpty() ? right + 1 : deque.peekFirst();
                long blockCount = nextBoundary - poppedIdx;

                currentCost += (long) (nums[left] - nums[poppedIdx]) * blockCount;
            }
            deque.addFirst(left);

            // Shrink the window from the right if total operations exceed k
            while (currentCost > k) {
                int maxIdx = deque.peekLast();
                currentCost -= (long) (nums[maxIdx] - nums[right]);

                if (maxIdx == right) {
                    deque.pollLast();
                }
                right--;
            }

            // Every subarray starting at 'left' and ending at or before 'right' is valid
            validSubarrays += (right - left + 1);
        }

        return validSubarrays;
    }
}
