/*
Problem: Valid Palindrome
Link: https://leetcode.com/problems/valid-palindrome/
Difficulty: Easy
Topic: String, Two Pointers

Approach:
We use the two-pointer technique to check whether the string is a palindrome
while ignoring non-alphanumeric characters and case differences.

Two pointers start at the beginning and end of the string. If a character is
not alphanumeric, we skip it. When both pointers point to valid characters,
we compare them after converting to lowercase. If they differ, the string
cannot be a palindrome.

Time Complexity: O(n)
- Each character is visited at most once.

Space Complexity: O(1)
- No extra space is used apart from pointer variables.

Extended Description:
This problem is a classic application of the two-pointer pattern for strings.
Instead of preprocessing the string to remove invalid characters, we handle
filtering on the fly during traversal. This keeps the solution memory-efficient
and avoids creating extra strings. The important detail is using
Character.isLetterOrDigit() to filter valid characters and converting both
characters to lowercase before comparison so the palindrome check becomes
case-insensitive.
*/

class Solution {

    public boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        // Move both pointers towards the center
        while (left < right) {

            // Skip characters that are not letters or digits
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // Skip characters from the right side as well
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare characters in a case-insensitive way
            if (Character.toLowerCase(s.charAt(left)) !=
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            // Move pointers inward
            left++;
            right--;
        }

        // If all characters matched
        return true;
    }
}
