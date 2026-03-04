/*
Problem: Substring with Concatenation of All Words (LeetCode #30)
Link: https://leetcode.com/problems/substring-with-concatenation-of-all-words/
Difficulty: Hard
Topic: Strings, Sliding Window, HashMap
Approach: Sliding window with word-frequency maps
Time Complexity: O(n * wordLen)
Space Complexity: O(m) // m = number of unique words

Extended Description:
This solution finds all starting indices of substrings formed by concatenating
all words exactly once without any intervening characters.

Steps:
1. Build a frequency map of the given words.
2. Iterate over all possible starting offsets (0 to word length - 1) to cover
   all alignments.
3. Use a sliding window to track current words and their counts.
4. If a word exceeds its allowed frequency, shrink the window from the left.
5. When the window contains all words exactly once, record the starting index.
6. Reset the window when encountering an invalid word.

Key Insight:
- Breaking the string into fixed-length word segments allows O(n) scanning per offset.
- Using two hash maps (one for target frequencies, one for current window) enables
  quick frequency checks and window adjustments.
- Sliding window pattern is essential to handle consecutive concatenated words efficiently.
*/

import java.util.*;

class Solution {

    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return result;
        
        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        
        // Build frequency map of given words
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        
        // Iterate over possible starting offsets to handle all alignments
        for (int i = 0; i < wordLen; i++) {
            int left = i, right = i, count = 0;
            Map<String, Integer> windowMap = new HashMap<>();
            
            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;
                
                if (wordMap.containsKey(word)) {
                    windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                    count++;
                    
                    // Shrink window if frequency exceeds target
                    while (windowMap.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }
                    
                    // If current window contains all words exactly once
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Invalid word, reset window
                    windowMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }
        
        return result;
    }

    /*
     * - Using word-length aligned sliding window avoids excessive substring scans.
     * - Two hash maps allow quick comparison and window adjustment.
     * - This pattern generalizes to many fixed-length concatenation substring problems.
     */
}
