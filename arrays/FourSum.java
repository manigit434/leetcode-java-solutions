/*
Problem: 4Sum
Link: https://leetcode.com/problems/4sum/
Difficulty: Medium
Topic: Array / Two Pointers
Approach: Sorting + Two Pointers
Time Complexity: O(n^3)
Space Complexity: O(1) extra (excluding result list)

Extended Description:
This solution finds all unique quadruplets in an array that sum to a target value. 
The array is first sorted to make it easier to skip duplicates. 
Two nested loops fix the first two numbers, and two pointers find the remaining numbers. 
We carefully skip duplicates to ensure uniqueness. 
This approach demonstrates mastery of sorting, two-pointer technique, and handling of duplicates in array problems, which is commonly asked in technical interviews.
*/

import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;

        Arrays.sort(nums);  // Sort array to handle duplicates

        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // skip duplicates

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // skip duplicates

                int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) left++;  // skip duplicates
                        while (left < right && nums[right] == nums[right + 1]) right--; // skip duplicates
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return res;
    }
}
