class Solution {
    public boolean rotateString(String s, String goal) {
        // Time Complexity: O(N) where N is the length of string s
        // Space Complexity: O(N) for the concatenated string
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}
