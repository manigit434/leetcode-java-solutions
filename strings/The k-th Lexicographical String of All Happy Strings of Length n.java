class Solution {
    private int count = 0;
    private String result = "";

    public String getHappyString(int n, int k) {
        dfs(n, k, new StringBuilder());
        return result;
    }

    private void dfs(int n, int k, StringBuilder path) {
        if (result.length() > 0) return;

        if (path.length() == n) {
            count++;
            if (count == k) result = path.toString();
            return;
        }

        for (char ch : new char[]{'a', 'b', 'c'}) {
            if (path.length() > 0 && path.charAt(path.length() - 1) == ch) continue;

            path.append(ch);
            dfs(n, k, path);
            path.deleteCharAt(path.length() - 1);
        }
    }

    // Time: O(3 * 2^(n-1))  Space: O(n)
}
