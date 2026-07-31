import java.util.Arrays;

public class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count the frequency of each lowercase English letter
        int[] frequency = new int[26];
        for (char c : word.toCharArray()) {
            frequency[c - 'a']++;
        }
        
        // Step 2: Sort frequencies in ascending order
        Arrays.sort(frequency);
        
        int totalPushes = 0;
        int distinctLettersProcessed = 0;
        
        // Step 3: Iterate from the highest frequency to the lowest
        for (int i = 25; i >= 0; i--) {
            if (frequency[i] == 0) {
                break; // No more characters to process
            }
            
            // Calculate how many pushes are needed for this character position.
            // Every group of 8 unique characters increases the required pushes by 1.
            int pushMultiplier = (distinctLettersProcessed / 8) + 1;
            
            totalPushes += frequency[i] * pushMultiplier;
            distinctLettersProcessed++;
        }
        
        return totalPushes;
    }
}
