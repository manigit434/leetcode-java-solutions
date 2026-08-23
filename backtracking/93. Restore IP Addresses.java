import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        // An IP address has 4 segments, each 1-3 chars long. Max length = 12.
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int startIndex, List<String> current, List<String> result) {
        // If we found 4 valid segments and reached the end of the string, record it
        if (current.size() == 4) {
            if (startIndex == s.length()) {
                result.add(String.join(".", current));
            }
            return;
        }

        // Pruning: remaining characters must fit in remaining available segments
        int remainingSegments = 4 - current.size();
        int remainingChars = s.length() - startIndex;
        if (remainingChars < remainingSegments || remainingChars > remainingSegments * 3) {
            return;
        }

        // Try forming a segment of length 1, 2, or 3
        for (int len = 1; len <= 3 && startIndex + len <= s.length(); len++) {
            String segment = s.substring(startIndex, startIndex + len);
            
            // Check for leading zero rule or value out of bounds (> 255)
            if ((segment.startsWith("0") && segment.length() > 1) || (len == 3 && Integer.parseInt(segment) > 255)) {
                continue;
            }

            current.add(segment);
            backtrack(s, startIndex + len, current, result);
            current.remove(current.size() - 1); // Backtrack
        }
    }
}
