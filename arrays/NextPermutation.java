/*
Problem: Next Permutation
Link: https://leetcode.com/problems/next-permutation/
Difficulty: Medium
Topic: Array, Two Pointers
Approach: Find pivot, swap, and reverse suffix

Time Complexity: O(n)
Space Complexity: O(1)

Extended Description:
This problem asks us to rearrange numbers into the lexicographically next permutation
of integers. If no such permutation exists (the array is in descending order),
we need to transform it into the lowest possible order (ascending).

The approach works in three steps:

1. **Find the pivot:** Scan from right to left to find the first element that is
   smaller than its next element. This is where the next permutation change will occur.
2. **Swap with successor:** From the right end, find the first number greater than
   the pivot and swap them. This ensures the next permutation is just slightly larger.
3. **Reverse the suffix:** Reverse the subarray to the right of the pivot to get
   the smallest possible order in that segment.

This method is in-place and does not require extra memory. It’s a classic pattern
for generating the next permutation efficiently.
*/

class Solution {

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // Step 1: find the first decreasing element
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // Step 2: find the element just larger than nums[i]
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            // swap nums[i] and nums[j]
            swap(nums, i, j);
        }

        // Step 3: reverse the subarray nums[i+1..n-1]
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
