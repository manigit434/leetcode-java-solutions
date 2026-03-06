/*
Problem: Check If Binary String Has at Most One Segment of Ones
Link: https://leetcode.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones/
Difficulty: Easy
Topic: String Traversal, Greedy

Approach:
The idea is to verify that the binary string contains only one continuous block of '1's.
While scanning the string from left to right, once we encounter a '0', we mark that a zero
has appeared. If we later see another '1' after this zero, it means a new segment of '1's
has started, which violates the condition.

Instead of explicitly counting segments, we track whether a '0' has already been seen.
If a '1' appears after a '0', we immediately return false.

Time Complexity: O(n)
- We traverse the string once.

Space Complexity: O(1)
- Only a boolean flag is used.

Extended Description:
This is a straightforward single-pass greedy check. The core observation is that a valid
string must look like: 111...1100...00 or just 111... or just 000.... Once the sequence
of '1's ends, it should never start again. Tracking the first occurrence of '0' is enough
to detect any violation. This approach avoids unnecessary segment counting and keeps the
logic clean and interview-friendly.
*/

class Solution {

    public boolean checkOnesSegment(String s) {

        // Flag to track whether we have already encountered a '0'
        boolean zeroFound = false;

        // Traverse the string character by character
        for (char c : s.toCharArray()) {

            // If a '0' appears, mark it
            if (c == '0') {
                zeroFound = true;
            }

            // If we encounter '1' after a '0', that means a new segment of ones started
            else if (zeroFound) {
                return false;
            }
        }

        // If no violation was found, the string has at most one segment of '1's
        return true;
    }
}
