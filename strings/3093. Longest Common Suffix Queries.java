class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int bestIndex = -1; // Stores index of shortest/earliest string passing through
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        TrieNode root = new TrieNode();
        int minLenIdx = 0;

        // Find the overall shortest/earliest string for the root (0 common suffix case)
        for (int i = 0; i < wordsContainer.length; i++) {
            if (wordsContainer[i].length() < wordsContainer[minLenIdx].length()) {
                minLenIdx = i;
            }
        }
        root.bestIndex = minLenIdx;

        // Build the Trie using reversed strings
        for (int i = 0; i < wordsContainer.length; i++) {
            String s = wordsContainer[i];
            TrieNode curr = root;
            for (int j = s.length() - 1; j >= 0; j--) {
                int charIdx = s.charAt(j) - 'a';
                if (curr.children[charIdx] == null) {
                    curr.children[charIdx] = new TrieNode();
                    curr.children[charIdx].bestIndex = i;
                } else {
                    // Update bestIndex if current string is shorter
                    int existingIdx = curr.children[charIdx].bestIndex;
                    if (s.length() < wordsContainer[existingIdx].length()) {
                        curr.children[charIdx].bestIndex = i;
                    }
                }
                curr = curr.children[charIdx];
            }
        }

        // Process Queries
        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            String q = wordsQuery[i];
            TrieNode curr = root;
            int resultIdx = root.bestIndex; 
            
            for (int j = q.length() - 1; j >= 0; j--) {
                int charIdx = q.charAt(j) - 'a';
                if (curr.children[charIdx] != null) {
                    curr = curr.children[charIdx];
                    resultIdx = curr.bestIndex;
                } else {
                    break;
                }
            }
            ans[i] = resultIdx;
        }

        return ans;
    }
}
