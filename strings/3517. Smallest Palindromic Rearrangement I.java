class Solution {
    public String smallestPalindrome(String s) {
        // Count frequencies of each lowercase English letter
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        StringBuilder firstHalf = new StringBuilder();
        char middleChar = 0;
        
        // Build the first half and find the odd character (if any)
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                middleChar = (char) (i + 'a');
            }
            // Append half of the occurrences to the first half
            for (int j = 0; j < count[i] / 2; j++) {
                firstHalf.append((char) (i + 'a'));
            }
        }
        
        // Create the second half by reversing the first half
        String secondHalf = new StringBuilder(firstHalf).reverse().toString();
        
        // Assemble the final smallest palindrome
        if (middleChar != 0) {
            firstHalf.append(middleChar);
        }
        firstHalf.append(secondHalf);
        
        return firstHalf.toString();
    }
}
