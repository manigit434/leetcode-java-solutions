class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0; // Number of '(' needed
        int closeNeeded = 0; // Number of ')' needed

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openNeeded++;
            } else {
                if (openNeeded > 0) {
                    openNeeded--; // Match with an existing '('
                } else {
                    closeNeeded++; // Unmatched ')' requires a new '('
                }
            }
        }

        return openNeeded + closeNeeded;
    }
}
