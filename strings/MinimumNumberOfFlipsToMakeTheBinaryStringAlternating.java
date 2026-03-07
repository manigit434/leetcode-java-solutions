class Solution {
    // Time: O(n), Space: O(n)
    public int minFlips(String s) {
        int n = s.length();
        String str = s + s;

        int mismatch01 = 0, mismatch10 = 0;
        int left = 0, res = Integer.MAX_VALUE;

        for (int right = 0; right < str.length(); right++) {
            char c = str.charAt(right);

            if (c != (right % 2 == 0 ? '0' : '1')) mismatch01++;
            if (c != (right % 2 == 0 ? '1' : '0')) mismatch10++;

            if (right - left + 1 > n) {
                char leftChar = str.charAt(left);
                if (leftChar != (left % 2 == 0 ? '0' : '1')) mismatch01--;
                if (leftChar != (left % 2 == 0 ? '1' : '0')) mismatch10--;
                left++;
            }

            if (right - left + 1 == n) {
                res = Math.min(res, Math.min(mismatch01, mismatch10));
            }
        }

        return res;
    }
}
