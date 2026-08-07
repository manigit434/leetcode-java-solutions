import java.util.Arrays;

public class Solution {
    public String smallestNumber(String num, long t) {
        long temp = t;
        // Check if t contains any prime factors other than 2, 3, 5, 7
        for (int i = 2; i <= 9; i++) {
            while (temp % i == 0) {
                temp /= i;
            }
        }
        if (temp > 1) {
            return "-1";
        }

        int n = num.length();
        // rem[i] stores the remaining required value of t after considering the prefix of length i
        long[] rem = new long[n + 1];
        rem[0] = t;
        
        int firstZeroPos = n;
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (c == '0') {
                firstZeroPos = i;
                break;
            }
            rem[i + 1] = rem[i] / gcd(rem[i], c - '0');
        }

        // Case 1: The original number is valid and contains no zeros
        if (firstZeroPos == n && rem[n] == 1) {
            return num;
        }

        // Case 2: Try to alter the string from right to left to find the smallest suffix change
        StringBuilder sb = new StringBuilder(num);
        for (int i = Math.min(n - 1, firstZeroPos); i >= 0; i--) {
            int startDigit = sb.charAt(i) - '0' + 1;
            for (int d = startDigit; d <= 9; d++) {
                long nextRem = rem[i] / gcd(rem[i], d);
                int remainingSlots = n - 1 - i;
                
                if (canForm(nextRem, remainingSlots)) {
                    sb.setCharAt(i, (char) ('0' + d));
                    fillGreedily(sb, i + 1, n - 1, nextRem);
                    return sb.toString();
                }
            }
        }

        // Case 3: If no valid combination exists within the same length, expand the length
        int targetLen = n + 1;
        while (!canForm(t, targetLen)) {
            targetLen++;
        }
        
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < targetLen; i++) {
            res.append('1');
        }
        fillGreedily(res, 0, targetLen - 1, t);
        return res.toString();
    }

    // Helper method to check if 'rem' can be formed using single digits within 'slots' count
    private boolean canForm(long rem, int slots) {
        int requiredDigits = 0;
        // Count how many factors of 9, 8, 7, 6, 5, 4, 3, 2 are needed sequentially
        for (int i = 9; i >= 2; i--) {
            while (rem % i == 0) {
                requiredDigits++;
                rem /= i;
            }
        }
        return rem == 1 && requiredDigits <= slots;
    }

    // Helper to fill the suffix of the string builder with the lexicographically smallest digits
    private void fillGreedily(StringBuilder sb, int start, int end, long rem) {
        // Collect single digit counts needed to satisfy 'rem'
        int[] counts = new int[10];
        for (int i = 9; i >= 2; i--) {
            while (rem % i == 0) {
                counts[i]++;
                rem /= i;
            }
        }

        // Place larger digits at the extreme right positions to minimize final value
        int idx = end;
        for (int d = 9; d >= 2; d--) {
            while (counts[d] > 0) {
                sb.setCharAt(idx--, (char) ('0' + d));
                counts[d]--;
            }
        }
        
        // Pad the remaining blank positions on the left side of the suffix with '1'
        while (idx >= start) {
            sb.setCharAt(idx--, '1');
        }
    }

    // Standard Greatest Common Divisor calculation using Euclidean algorithm
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
