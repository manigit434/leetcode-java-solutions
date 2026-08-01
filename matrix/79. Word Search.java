class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        // Pruning: Check if board has enough characters for the word
        int[] boardCount = new int[128];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boardCount[board[i][j]]++;
            }
        }
        
        int[] wordCount = new int[128];
        for (char c : word.toCharArray()) {
            wordCount[c]++;
            if (wordCount[c] > boardCount[c]) return false;
        }

        // Optimization: Reverse word if the last letter appears less frequently than the first
        if (wordCount[word.charAt(0)] > wordCount[word.charAt(word.length() - 1)]) {
            word = new StringBuilder(word).reverse().toString();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int idx) {
        if (idx == word.length()) return true;
        
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] != word.charAt(idx)) {
            return false;
        }

        char temp = board[r][c];
        board[r][c] = '#'; // Mark as visited

        boolean found = dfs(board, word, r + 1, c, idx + 1) ||
                        dfs(board, word, r - 1, c, idx + 1) ||
                        dfs(board, word, r, c + 1, idx + 1) ||
                        dfs(board, word, r, c - 1, idx + 1);

        board[r][c] = temp; // Backtrack
        return found;
    }
}
