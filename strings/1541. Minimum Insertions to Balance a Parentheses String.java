class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRights = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // If we need an odd number of ')', it means the previous pair was incomplete.
                // We must insert 1 ')' to fix it before starting a new group.
                if (neededRights % 2 != 0) {
                    insertions++;
                    neededRights--;
                }
                neededRights += 2;
            } else {
                // We encountered a ')'
                neededRights--;
                
                // If neededRights becomes negative, we have an unmatched ')'
                // We must insert a '(' to match it.
                if (neededRights < 0) {
                    insertions++;    // Insert 1 '('
                    neededRights += 2; // The new '(' wants 2 ')', but we already used 1 right now
                }
            }
        }
        
        // Any remaining needed rights must be inserted at the end
        return insertions + neededRights;
    }
}
