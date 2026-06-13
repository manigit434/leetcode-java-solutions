class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder result = new StringBuilder();
        
        for (String word : words) {
            int totalWeight = 0;
            
            for (int i = 0; i < word.length(); i++) {
                totalWeight += weights[word.charAt(i) - 'a'];
            }
            
            int remainder = totalWeight % 26;
            char mappedChar = (char) ('z' - remainder);
            result.append(mappedChar);
        }
        
        return result.toString();
    }
}
/*
 * Time Complexity: O(N * L) where N is the number of words and L is the maximum length of a word.
 * Space Complexity: O(1) auxiliary space, as the StringBuilder holds the output sequence.
 */
