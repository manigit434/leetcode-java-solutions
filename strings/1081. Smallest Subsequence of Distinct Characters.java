class Solution {
    public String smallestSubsequence(String s) {
        int[] count = new int[26];
        boolean[] inStack = new boolean[26];
        char[] chars = s.toCharArray();
        
        // Count frequencies of each character
        for (char c : chars) {
            count[c - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (char c : chars) {
            count[c - 'a']--;
            
            // If the character is already in the stack, skip it
            if (inStack[c - 'a']) continue;
            
            // Maintain lexicographical order
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) > c && count[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                inStack[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            
            // Add to stack
            sb.append(c);
            inStack[c - 'a'] = true;
        }
        
        return sb.toString();
    }
}
