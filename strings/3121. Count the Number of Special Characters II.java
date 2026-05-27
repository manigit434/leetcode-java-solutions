class Solution {
    public int numberOfSpecialChars(String word) {
        // Time Complexity: O(N) 
        // Space Complexity: O(1) 
        
        int[] lastLowercase = new int[26];
        int[] firstUppercase = new int[26];
        boolean[] isUppercaseSeen = new boolean[26];

        
        java.util.Arrays.fill(lastLowercase, -1);
        java.util.Arrays.fill(firstUppercase, -1);

        
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (Character.isLowerCase(ch)) {
                lastLowercase[ch - 'a'] = i;
            } else {
                int upperIndex = ch - 'A';
                if (!isUppercaseSeen[upperIndex]) {
                    firstUppercase[upperIndex] = i;
                    isUppercaseSeen[upperIndex] = true;
                }
            }
        }

        int specialCount = 0;

        
        for (int i = 0; i < 26; i++) {
            
            if (lastLowercase[i] != -1 && firstUppercase[i] != -1) {
                
                if (lastLowercase[i] < firstUppercase[i]) {
                    specialCount++;
                }
            }
        }

        return specialCount;
    }
}
