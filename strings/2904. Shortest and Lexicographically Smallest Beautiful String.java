class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int bestLeft = -1;
        int minLength = s.length() + 1;
        int onesCount = 0;
        int left = 0;

        // Expand the window with the right pointer
        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(right) == '1') {
                onesCount++;
            }

            // Shrink the window from the left as long as it contains exactly k '1's
            while (onesCount == k) {
                int currentLength = right - left + 1;

                // Found a shorter beautiful substring
                if (currentLength < minLength) {
                    minLength = currentLength;
                    bestLeft = left;
                } 
                // Found a candidate of the same length; compare lexicographically
                else if (currentLength == minLength) {
                    String currentSub = s.substring(left, left + minLength);
                    String bestSub = s.substring(bestLeft, bestLeft + minLength);
                    if (currentSub.compareTo(bestSub) < 0) {
                        bestLeft = left;
                    }
                }

                // Slide the left pointer forward
                if (s.charAt(left) == '1') {
                    onesCount--;
                }
                left++;
            }
        }

        // Return empty string if no valid substring was found
        return bestLeft == -1 ? "" : s.substring(bestLeft, bestLeft + minLength);
    }
}
