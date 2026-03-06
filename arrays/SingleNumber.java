/*
Problem: Single Number
Link: https://leetcode.com/problems/single-number/
Difficulty: Easy
Topic: Array, Bit Manipulation
Approach: XOR Trick

Time Complexity: O(n)
Space Complexity: O(1)

Extended Description:
The task is to find the element that appears exactly once in an array where every other
element appears twice. A very clean and optimal way to solve this is by using the XOR
bitwise operator.

XOR has two key properties that make this possible:
1. a ^ a = 0  → a number XORed with itself cancels out
2. a ^ 0 = a  → a number XORed with zero stays the same

If we XOR all elements in the array, duplicate numbers eliminate each other,
and the only remaining value will be the number that appears once.

This approach avoids extra memory structures like HashMaps and does the work
in a single pass. It is a classic pattern for problems where elements appear
twice except one unique element.
*/

class Solution {

    public int singleNumber(int[] nums) {

        // Stores cumulative XOR of elements
        int result = 0;

        /*
         * Thought Process:
         * XOR all numbers together.
         * Duplicate numbers cancel each other out.
         * Only the unique number remains.
         *
         * Example:
         * nums = [2,2,1]
         *
         * Step-by-step:
         * 0 ^ 2 = 2
         * 2 ^ 2 = 0
         * 0 ^ 1 = 1
         *
         * Final answer = 1
         */

        for (int num : nums) {
            result ^= num; // XOR operation
        }

        return result;
    }
}
