/*
Problem: Strong Password Checker
Link: https://leetcode.com/problems/strong-password-checker/
Difficulty: Hard

Topics: String, Greedy, Math

Approach:
The goal is to find the minimum number of steps to make a password strong. 
A strong password satisfies:
1. Length between 6 and 20
2. At least one lowercase, one uppercase, and one digit
3. No three repeating characters in a row

Algorithm:
1. Count missing character types (lowercase, uppercase, digit)
2. Find repeating character sequences of length ≥3
3. Calculate the number of replacements needed (len / 3 per run)
4. Handle three cases:
   a) Length < 6 → need insertions; answer = max(missingTypes, 6 - n)
   b) Length 6–20 → only replacements matter; answer = max(missingTypes, replacements)
   c) Length > 20 → need deletions to reduce length:
      - Greedily delete characters in repeating runs to reduce replacements
      - Prioritize runs with len % 3 == 0, then 1, then 2
      - Remaining deletions reduce replacements by floor(delete / 3)
      - Total steps = deletions + max(missingTypes, updated replacements)

Time Complexity: O(n) → single pass to check types and runs, and process deletions
Space Complexity: O(n) → storing repeating runs

Example:
Input: "a"
- Length < 6 → need 5 insertions or replacements
- Missing types: uppercase + digit → 2
- Answer: max(5, 2) = 5

Insight:
The key is **greedy reduction of repeating sequences** and separately
tracking missing types. Handling short, medium, and long passwords
differently simplifies logic.
*/

import java.util.*;

public class Solution {

    public int strongPasswordChecker(String s) {
        int n = s.length();
        boolean hasLower = false, hasUpper = false, hasDigit = false;
        char[] arr = s.toCharArray();

        // Count missing character types
        for (char c : arr) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
        }
        int missingTypes = 0;
        if (!hasLower) missingTypes++;
        if (!hasUpper) missingTypes++;
        if (!hasDigit) missingTypes++;

        // Collect repeating sequences length ≥ 3
        List<Integer> runs = new ArrayList<>();
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && arr[j] == arr[i]) j++;
            int len = j - i;
            if (len >= 3) runs.add(len);
            i = j;
        }

        // Initial replacements needed for repeats
        int replacements = 0;
        for (int len : runs) replacements += len / 3;

        // Case 1: short password → need insertions
        if (n < 6) {
            return Math.max(missingTypes, 6 - n);
        } 
        // Case 2: acceptable length → only replacements needed
        else if (n <= 20) {
            return Math.max(missingTypes, replacements);
        } 
        // Case 3: long password → need deletions
        else {
            int delete = n - 20;

            // Convert runs to array for in-place updates
            int[] lens = new int[runs.size()];
            for (int i = 0; i < runs.size(); i++) lens[i] = runs.get(i);

            // Greedy deletions to reduce replacements
            for (int mod = 0; mod < 3 && delete > 0; mod++) {
                for (int i = 0; i < lens.length && delete > 0; i++) {
                    if (lens[i] < 3 || lens[i] % 3 != mod) continue;
                    int need = Math.min(delete, mod + 1);
                    lens[i] -= need;
                    delete -= need;
                }
            }

            // Recompute replacements after deletions
            replacements = 0;
            for (int len : lens) {
                if (len >= 3) replacements += len / 3;
            }

            // Any remaining deletions reduce replacements further
            if (delete > 0) {
                replacements = Math.max(0, replacements - delete / 3);
            }

            // Total steps = deletions + max(missing types, replacements)
            return (n - 20) + Math.max(missingTypes, replacements);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.strongPasswordChecker("a"));         // 5
        System.out.println(sol.strongPasswordChecker("aA1"));       // 3
        System.out.println(sol.strongPasswordChecker("1337C0d3"));  // 0
    }
}
