class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] lengths = new long[n];
        long currentLength = 0;

        // Step 1: Precompute the lengths at each operation from left to right
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                currentLength++;
            } else if (c == '*') {
                if (currentLength > 0) {
                    currentLength--;
                }
            } else if (c == '#') {
                currentLength *= 2;
            }
            // '%' (reverse) does not change the string length
            lengths[i] = currentLength;
        }

        // Return out of bounds marker if k is invalid or exceeds the final length
        if (k < 0 || n == 0 || k >= lengths[n - 1]) {
            return '.';
        }

        // Step 2: Backtrack from right to left to locate index k
        return findKthChar(s, n - 1, k, lengths);
    }

    private char findKthChar(String s, int index, long k, long[] lengths) {
        if (index < 0) {
            return '.';
        }

        char c = s.charAt(index);

        if (c >= 'a' && c <= 'z') {
            long prevLen = (index > 0) ? lengths[index - 1] : 0;
            // If k matches the index of this newly appended character
            if (k == prevLen) {
                return c;
            }
            // Otherwise, it belongs to the earlier prefix string
            return findKthChar(s, index - 1, k, lengths);
        } else if (c == '*') {
            // Deletion only removes the end; index k maps directly to index k in the prefix
            return findKthChar(s, index - 1, k, lengths);
        } else if (c == '#') {
            long prevLen = (index > 0) ? lengths[index - 1] : 0;
            // If k falls in the duplicated second half, map it back to the first half
            if (k >= prevLen) {
                k -= prevLen;
            }
            return findKthChar(s, index - 1, k, lengths);
        } else if (c == '%') {
            long prevLen = (index > 0) ? lengths[index - 1] : 0;
            // Reverse operation flips the indices of the prefix string
            return findKthChar(s, index - 1, prevLen - 1 - k, lengths);
        }

        return '.';
    }
}
