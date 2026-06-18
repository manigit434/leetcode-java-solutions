import java.util.HashMap;
import java.util.Map;

class Solution {
    // Cache to store results of evaluated string pairs to avoid redundant work
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        // Base case: if strings are identical, they are valid scrambles
        if (s1.equals(s2)) {
            return true;
        }

        // Base case: if lengths differ, they can never match
        if (s1.length() != s2.length()) {
            return false;
        }

        // Create a unique key for the current pair of strings
        String key = s1 + "," + s2;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int n = s1.length();
        
        // Optimization: check if both strings have the exact same character frequencies
        int[] counts = new int[26];
        for (int i = 0; i < n; i++) {
            counts[s1.charAt(i) - 'a']++;
            counts[s2.charAt(i) - 'a']--;
        }
        
        // If they are not anagrams, one cannot be transformed into the other
        for (int count : counts) {
            if (count != 0) {
                memo.put(key, false);
                return false;
            }
        }

        // Try splitting the strings at every possible index 'i'
        for (int i = 1; i < n; i++) {
            // Case 1: Substrings are NOT swapped at this level
            // Front matches front, and back matches back
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && 
                isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(key, true);
                return true;
            }

            // Case 2: Substrings ARE swapped at this level
            // Front of s1 matches back of s2, and back of s1 matches front of s2
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) && 
                isScramble(s1.substring(i), s2.substring(0, n - i))) {
                memo.put(key, true);
                return true;
            }
        }

        // If no split strategy successfully matches the two strings, cache false
        memo.put(key, false);
        return false;
    }
}
