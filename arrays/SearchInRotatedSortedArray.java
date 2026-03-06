/*
Problem: Search in Rotated Sorted Array
Link: https://leetcode.com/problems/search-in-rotated-sorted-array/
Difficulty: Medium
Topic: Array, Binary Search
Approach: Modified Binary Search on Rotated Array

Time Complexity: O(log n)
Space Complexity: O(1)

Extended Description:
We are given a rotated sorted array and need to find a target element in O(log n) time.
The key insight is that **even after rotation, at least one half of the array is sorted**. 
We can use a modified binary search:

1. Find the mid element of the current search window.
2. Check which half (left or right) is sorted.
3. If the target lies within the sorted half, narrow search to that half.
4. Otherwise, search the other half.
5. Repeat until the target is found or search space is exhausted.

This approach leverages the fact that rotation preserves the sorted order in one half, 
allowing binary search to remain efficient. No extra space is needed.
*/

class Solution {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if mid is the target
            if (nums[mid] == target) return mid;

            // Determine which half is sorted
            if (nums[left] <= nums[mid]) {
                // Left half is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // target is in left half
                } else {
                    left = mid + 1;  // target is in right half
                }
            } else {
                // Right half is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;  // target is in right half
                } else {
                    right = mid - 1; // target is in left half
                }
            }
        }

        // target not found
        return -1;
    }
}
