class Solution {
    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood(i)) {
                count++;
            }
        }
        return count;
    }
    
    private boolean isGood(int n) {
        boolean diff = false;
        while (n > 0) {
            int d = n % 10;
            if (d == 2 || d == 5 || d == 6 || d == 9) {
                diff = true;
            } else if (d != 0 && d != 1 && d != 8) {
                return false;
            }
            n /= 10;
        }
        return diff;
    }
}
// Time Complexity: O(n log10(n)) - Iterating up to n, and each number takes log10(n) to process digits.
// Space Complexity: O(1) - Constant extra space used.
