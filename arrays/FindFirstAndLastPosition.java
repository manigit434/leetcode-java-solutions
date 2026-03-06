/*
Problem: Find First and Last Position of Element in Sorted Array
Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
Difficulty: Medium
Topic: Array, Binary Search
Approach: Modified Binary Search to Find Boundaries

Time Complexity: O(log n)
Space Complexity: O(1)

Extended Description:
This problem requires finding the first and last positions of a target element
in a sorted array. A naive approach would be linear search, but we can achieve
O(log n) using binary search.

The key idea is **finding the boundaries**:
1. Use a binary search to locate the **first occurrence** of the target:
   - When nums[mid] == target, continue searching left (right = mid - 1) to ensure first index.
2. Use a binary search to locate the **last occurrence**:
   - When nums[mid] == target, continue searching right (left = mid + 1) to ensure last index.
3. If the target is not found, return [-1, -1].

This is a standard pattern for “find boundary indices in a sorted array” problems. 
It’s efficient, in-place, and leverages the power of binary search smartly.
*/

class Solution {

    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        if (first == -1) return new int[]{-1, -1}; // target not found
        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1;
        int bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) {
                    // move left to find first occurrence
                    right = mid - 1;
                } else {
                    // move right to find last occurrence
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return bound;
    }
}
