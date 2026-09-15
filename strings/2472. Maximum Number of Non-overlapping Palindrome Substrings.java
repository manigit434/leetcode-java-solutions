class Solution {
    public int maxPalindromes(String s, int k) {
        int count = 0;
        int n = s.length();
        int lastEnd = -1; // Tracks the end index of the last selected palindrome

        for (int i = 0; i < n; i++) {
            // Case 1: Check for odd-length palindromes centered at i
            int oddLenEnd = findPalindromeEnd(s, i, i, k, lastEnd);
            
            // Case 2: Check for even-length palindromes centered at i and i+1
            int evenLenEnd = findPalindromeEnd(s, i, i + 1, k, lastEnd);
            
            // If both found a valid palindrome, greedily pick the one that ends earlier
            int bestEnd = -1;
            if (oddLenEnd != -1 && evenLenEnd != -1) {
                bestEnd = Math.min(oddLenEnd, evenLenEnd);
            } else if (oddLenEnd != -1) {
                bestEnd = oddLenEnd;
            } else {
                bestEnd = evenLenEnd;
            }

            // If a valid palindrome is found, register it and jump the pointer
            if (bestEnd != -1) {
                count++;
                lastEnd = bestEnd;
                i = bestEnd; // Greedy jump: move past the selected palindrome
            }
        }
        return count;
    }

    private int findPalindromeEnd(String s, int l, int r, int k, int lastEnd) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            // Ensure the palindrome does not overlap with the previously selected one
            if (l <= lastEnd) {
                break;
            }
            // Check if length condition is satisfied
            if (r - l + 1 >= k) {
                return r; // Return the ending index
            }
            l--;
            r++;
        }
        return -1;
    }
}
