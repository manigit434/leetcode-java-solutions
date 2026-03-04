/*
Problem: Divide Two Integers (LeetCode #29)
Link: https://leetcode.com/problems/divide-two-integers/
Difficulty: Medium
Topic: Math, Bit Manipulation
Approach: Bitwise subtraction and doubling (without *, /, %)
Time Complexity: O(log(dividend)^2)
Space Complexity: O(1)

Extended Description:
The goal is to perform integer division without using *, /, or % operators.
This solution uses bit manipulation and subtraction.

Steps:
1. Handle overflow edge case: dividing Integer.MIN_VALUE by -1 exceeds Integer.MAX_VALUE.
2. Determine the sign of the result using XOR.
3. Convert dividend and divisor to long and take absolute values to avoid overflow.
4. Repeatedly subtract multiples of the divisor from the dividend.
   - To optimize, double the divisor (using left shift) and track multiples.
   - Subtract the largest possible multiple each iteration to reduce steps.
5. Accumulate the multiples in the result.
6. Apply the sign before returning.

Key Insight:
Doubling the divisor reduces the number of iterations from O(n) to O(log n),
where n is the dividend. Using long prevents overflow from Integer.MIN_VALUE.

This is a classic bit-manipulation pattern for division problems without operators.
*/

class Solution {

    public int divide(int dividend, int divisor) {

        // Edge case: overflow scenario
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        // Determine the sign of the result
        boolean negative = (dividend < 0) ^ (divisor < 0);
        
        // Use long to handle overflow and take absolute values
        long dividendL = Math.abs((long) dividend);
        long divisorL = Math.abs((long) divisor);
        
        int result = 0;
        
        // Subtract multiples of divisor using bit shifts
        while (dividendL >= divisorL) {
            long temp = divisorL;
            int multiple = 1;
            
            // Keep doubling until it exceeds the dividend
            while (dividendL >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            
            // Subtract largest multiple and accumulate result
            dividendL -= temp;
            result += multiple;
        }
        
        // Apply sign
        return negative ? -result : result;
    }

    /*
     * Thought Process:
     * - Naive subtraction is too slow (O(n)), doubling optimizes to O(log n).
     * - Bit shifts replace multiplication/division.
     * - Always handle overflow carefully, especially with Integer.MIN_VALUE.
     * - Classic technique for division without operators.
     */
}
