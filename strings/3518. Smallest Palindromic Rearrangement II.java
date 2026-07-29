import java.util.Arrays;

class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int n = s.length();
        int halfLen = n / 2;
        int[] halfCount = new int[26];
        char oddChar = 0;

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
            if (count[i] % 2 != 0) {
                oddChar = (char) ('a' + i);
            }
        }

        // Check if total distinct permutations are fewer than k
        long totalWays = countPermutations(halfCount, halfLen, k);
        if (totalWays < k) {
            return "";
        }

        long remainingK = k;
        StringBuilder firstHalf = new StringBuilder();

        // Greedily build the first half character by character
        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (halfCount[c] == 0) continue;

                // Try placing character 'c' at the current position
                halfCount[c]--;
                long ways = countPermutations(halfCount, halfLen - 1 - pos, remainingK);

                if (remainingK <= ways) {
                    firstHalf.append((char) ('a' + c));
                    break; // Keep this character and move to next position
                } else {
                    remainingK -= ways;
                    halfCount[c]++; // Backtrack
                }
            }
        }

        // Construct the full palindrome
        StringBuilder result = new StringBuilder(firstHalf);
        if (oddChar != 0) {
            result.append(oddChar);
        }
        
        // Append the reversed first half to finish the palindrome
        result.append(new StringBuilder(firstHalf).reverse());

        return result.toString();
    }

    // Helper to calculate total permutations of a multiset safely with a cap limit
    private long countPermutations(int[] counts, int remLen, long limit) {
        long totalWays = 1;
        int currentRem = remLen;
        
        for (int c = 0; c < 26; c++) {
            if (counts[c] == 0) continue;
            
            long ways = nCr(currentRem, counts[c], limit);
            if (ways > limit) return limit + 1;
            
            totalWays *= ways;
            if (totalWays > limit) return limit + 1;
            
            currentRem -= counts[c];
        }
        return totalWays;
    }

    // Helper to calculate combinations nCr safely with a cap limit
    private long nCr(int n, int r, long limit) {
        if (r > n || r < 0) return 0;
        if (r == 0 || r == n) return 1;
        if (r > n / 2) r = n - r;
        
        long res = 1;
        for (int i = 1; i <= r; i++) {
            // Multiplications fit within long range because limit <= 10^6 and n <= 5000
            res = res * (n - i + 1) / i;
            if (res > limit) return limit + 1;
        }
        return res;
    }
}
