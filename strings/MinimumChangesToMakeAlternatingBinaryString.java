/*
Problem: Minimum Changes To Make Alternating Binary String
Link: https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/
Difficulty: Easy

Topics: String, Greedy

Approach:
An alternating binary string means no two adjacent characters are the same.
Such a string can only follow two possible patterns:

1. "010101..."  → starting with '0'
2. "101010..."  → starting with '1'

So instead of actually building these strings, we can simply compare the
input string with both patterns and count how many characters don't match.

While iterating:
- If we assume the string should start with '0', we track mismatches in `start0`
- If we assume the string should start with '1', we track mismatches in `start1`

At the end, the minimum of these two counts gives the answer.

Time Complexity: O(n)
Space Complexity: O(1)

Notes:
This is a classic pattern-checking problem. Since there are only two valid
alternating patterns, we just evaluate both and pick the one requiring
fewer flips.
*/

class Solution {

    public int minOperations(String s) {

        // flips needed if the string starts with '0' -> "010101..."
        int start0 = 0;

        // flips needed if the string starts with '1' -> "101010..."
        int start1 = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // For even indices, expected chars are different for both patterns
            if (i % 2 == 0) {

                if (ch != '0') start0++;  // mismatch for "0101..."
                if (ch != '1') start1++;  // mismatch for "1010..."

            } else {

                // For odd indices, expectations swap
                if (ch != '1') start0++;
                if (ch != '0') start1++;
            }
        }

        // Choose the pattern that requires fewer changes
        return Math.min(start0, start1);
    }
}
