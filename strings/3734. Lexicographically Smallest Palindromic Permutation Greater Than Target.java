import java.util.*;

public class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        // Validate palindrome possibility and find middle character
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (counts[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }
        
        if (oddCount > 1 || (oddCount == 1 && n % 2 == 0) || (oddCount == 0 && n % 2 != 0)) {
            return "";
        }
        
        // Character pool for the first half
        int[] halfCounts = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCounts[i] = counts[i] / 2;
        }
        
        int k = n / 2;
        String mStr = oddCount == 1 ? String.valueOf(midChar) : "";
        
        // Strategy 1: Attempt to match the first half exactly with target
        int[] currCounts = halfCounts.clone();
        boolean possibleStay = true;
        StringBuilder hStay = new StringBuilder();
        for (int i = 0; i < k; i++) {
            char tChar = target.charAt(i);
            if (currCounts[tChar - 'a'] > 0) {
                hStay.append(tChar);
                currCounts[tChar - 'a']--;
            } else {
                possibleStay = false;
                break;
            }
        }
        
        if (possibleStay) {
            String hStr = hStay.toString();
            String revHStr = hStay.reverse().toString();
            String pStay = hStr + mStr + revHStr;
            if (pStay.compareTo(target) > 0) {
                return pStay;
            }
        }
        
        // Strategy 2: Find the latest index 'i' where we can deviate to a larger character
        for (int i = k - 1; i >= 0; i--) {
            currCounts = halfCounts.clone();
            boolean canReach = true;
            for (int j = 0; j < i; j++) {
                char tChar = target.charAt(j);
                if (currCounts[tChar - 'a'] > 0) {
                    currCounts[tChar - 'a']--;
                } else {
                    canReach = false;
                    break;
                }
            }
            if (!canReach) continue;
            
            // Look for the smallest available character strictly greater than target.charAt(i)
            char tChar = target.charAt(i);
            int bestCharIdx = -1;
            for (int c = tChar - 'a' + 1; c < 26; c++) {
                if (currCounts[c] > 0) {
                    bestCharIdx = c;
                    break;
                }
            }
            
            if (bestCharIdx != -1) {
                currCounts[bestCharIdx]--;
                StringBuilder hDev = new StringBuilder(target.substring(0, i));
                hDev.append((char) ('a' + bestCharIdx));
                
                // Lexicographically fill the rest of the half with the smallest remaining characters
                for (int c = 0; c < 26; c++) {
                    while (currCounts[c] > 0) {
                        hDev.append((char) ('a' + c));
                        currCounts[c]--;
                    }
                }
                String hStr = hDev.toString();
                String revHStr = hDev.reverse().toString();
                return hStr + mStr + revHStr;
            }
        }
        
        return "";
    }
}
