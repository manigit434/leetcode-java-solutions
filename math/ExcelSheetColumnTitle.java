// Time: O(log26 n), Space: O(1)
class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();

        while (columnNumber > 0) {
            columnNumber--; // shift to 0-indexed
            sb.append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }

        return sb.reverse().toString();
    }
}
