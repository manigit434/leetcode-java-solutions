class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] targetCounts = new int[128];
        for (char c : t.toCharArray()) {
            targetCounts[c]++;
        }

        int[] windowCounts = new int[128];
        int requiredMatches = 0;
        for (int count : targetCounts) {
            if (count > 0) {
                requiredMatches++;
            }
        }

        int left = 0;
        int right = 0;
        int currentMatches = 0;
        
        int minLength = Integer.MAX_VALUE;
        int minLeft = 0;

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            windowCounts[rightChar]++;

            if (targetCounts[rightChar] > 0 && windowCounts[rightChar] == targetCounts[rightChar]) {
                currentMatches++;
            }

            while (currentMatches == requiredMatches) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }

                char leftChar = s.charAt(left);
                windowCounts[leftChar]--;

                if (targetCounts[leftChar] > 0 && windowCounts[leftChar] < targetCounts[leftChar]) {
                    currentMatches--;
                }
                
                left++;
            }
            
            right++;
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }
}
// Time Complexity: O(m + n) - where m is s.length and n is t.length; each character is visited at most twice.
// Space Complexity: O(1) - uses constant auxiliary space for fixed-size frequency arrays of size 128.
