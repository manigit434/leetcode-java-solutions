/*
Problem: Find Kth Bit in Nth Binary String
Link: https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/
Difficulty: Medium
Topic: Recursion / Divide & Conquer
Approach: Recursive binary string mapping without generating entire string
Time Complexity: O(n)
Space Complexity: O(n) recursion stack

Extended Description:
The sequence is built recursively as Sn = Sn-1 + "1" + reverse(invert(Sn-1)).
By observing the pattern:
- The middle bit is always "1".
- The left half corresponds to Sn-1.
- The right half is reversed and inverted Sn-1.
This allows us to find the kth bit recursively without constructing the full string, which is efficient for large n.
*/

class Solution {
    public char findKthBit(int n, int k) {
        if (n == 1) return '0'; // Base case S1
        
        int mid = 1 << (n - 1); // Middle index (1-based)
        
        if (k == mid) return '1'; // Middle bit is always '1'
        else if (k < mid) return findKthBit(n - 1, k); // Left half
        else {
            // Map k to mirrored position in left half and invert
            int mirroredIndex = (1 << n) - k;
            char c = findKthBit(n - 1, mirroredIndex);
            return c == '0' ? '1' : '0';
        }
    }
}
