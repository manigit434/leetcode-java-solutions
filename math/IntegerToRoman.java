/*
Problem: Integer to Roman
Link: https://leetcode.com/problems/integer-to-roman/
Difficulty: Medium

Topics: Math, Greedy, String

Approach:
We use a **greedy approach** with predefined mappings of integers to Roman numerals.
Roman numerals have specific symbols for certain values, including subtraction rules 
(e.g., 4 = IV, 9 = IX, 40 = XL).

Algorithm:
1. Create two arrays:
   - `values` stores integer values in descending order.
   - `symbols` stores the corresponding Roman numeral symbols.
2. Iterate over the arrays:
   - While the input number is greater than or equal to the current value,
     subtract it from the number and append the symbol to the result.
3. Continue until the input number is reduced to 0.

This approach ensures that we always pick the **largest possible numeral** first,
which naturally respects Roman numeral rules.

Time Complexity: O(1) → the number is bounded (1 ≤ num ≤ 3999)  
Space Complexity: O(1) → output string length ≤ 15 (for 3888: MMMDCCCLXXXVIII)

Example:
num = 1994  
- Take 1000 → 'M', remaining 994  
- Take 900 → 'CM', remaining 94  
- Take 90 → 'XC', remaining 4  
- Take 4 → 'IV', done → result = "MCMXCIV"

Insight:
Greedy mapping works because Roman numeral system always uses the **largest valid symbols first**, 
including subtraction combinations.
*/

class Solution {

    public String intToRoman(int num) {

        int[] values =    {1000, 900, 500, 400, 100, 90,  50, 40,  10, 9, 5, 4, 1};
        String[] symbols ={"M", "CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }
        
        return sb.toString();
    }
}
