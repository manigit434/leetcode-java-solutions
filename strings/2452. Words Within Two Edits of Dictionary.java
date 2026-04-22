import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();
        
        for (String q : queries) {
            for (String d : dictionary) {
                if (canMatch(q, d)) {
                    result.add(q);
                    break;
                }
            }
        }
        return result;
    }
    
    private boolean canMatch(String q, String d) {
        int diff = 0;
        for (int i = 0; i < q.length(); i++) {
            if (q.charAt(i) != d.charAt(i)) {
                diff++;
                if (diff > 2) return false;
            }
        }
        return diff <= 2;
    }
}
// Time Complexity: O(Q * D * N) where Q = queries.length, D = dictionary.length, N = length of strings.
// Space Complexity: O(R) where R is the number of words in the result list.
