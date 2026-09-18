import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        
        for (int i = 0; i < 26; i++) {
            left[i] = n;
            right[i] = -1;
        }
        
        // Step 1: Find first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            left[idx] = Math.min(left[idx], i);
            right[idx] = Math.max(right[idx], i);
        }
        
        List<String> res = new ArrayList<>();
        int lastRight = -1;
        
        // Step 2: Validate and expand valid intervals greedily
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (i != left[idx]) continue; // Only process at the start of a character's first appearance
            
            int r = getValidRight(s, i, left, right);
            if (r != -1) {
                if (i <= lastRight) {
                    res.set(res.size() - 1, s.substring(i, r + 1));
                } else {
                    res.add(s.substring(i, r + 1));
                }
                lastRight = r;
            }
        }
        
        return res;
    }
    
    private int getValidRight(String s, int start, int[] left, int[] right) {
        int r = right[s.charAt(start) - 'a'];
        for (int j = start; j <= r; j++) {
            int idx = s.charAt(j) - 'a';
            if (left[idx] < start) {
                return -1; // Invalid, leaks outside the desired start boundary
            }
            r = Math.max(r, right[idx]); // Expand boundary if needed
        }
        return r;
    }
}
