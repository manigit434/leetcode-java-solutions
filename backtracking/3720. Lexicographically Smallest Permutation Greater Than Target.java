import java.util.Arrays;

public class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        char[] result = new char[n];
        
        // Try to find the longest matching prefix with target
        if (canMatchPrefix(0, counts, target, result)) {
            return new String(result);
        }
        
        return "";
    }
    
    private boolean canMatchPrefix(int index, int[] counts, String target, char[] result) {
        int n = target.length();
        if (index == n) {
            // Reached the end and it's equal to target, which is not strictly greater
            return false; 
        }
        
        int targetCharIdx = target.charAt(index) - 'a';
        
        // Case 1: Try to match the target character exactly at this index
        if (counts[targetCharIdx] > 0) {
            result[index] = target.charAt(index);
            counts[targetCharIdx]--;
            if (canMatchPrefix(index + 1, counts, target, result)) {
                return true;
            }
            // Backtrack
            counts[targetCharIdx]++;
        }
        
        // Case 2: Diverge here by picking a character strictly GREATER than target.charAt(index)
        for (int c = targetCharIdx + 1; c < 26; c++) {
            if (counts[c] > 0) {
                result[index] = (char) ('a' + c);
                counts[c]--;
                
                // Once we are strictly greater, fill the rest with the smallest available characters
                fillSmallest(index + 1, counts, result);
                return true;
            }
        }
        
        return false;
    }
    
    private void fillSmallest(int index, int[] counts, char[] result) {
        int n = result.length;
        int currChar = 0;
        while (index < n) {
            while (currChar < 26 && counts[currChar] == 0) {
                currChar++;
            }
            result[index] = (char) ('a' + currChar);
            counts[currChar]--;
            index++;
        }
    }
}
