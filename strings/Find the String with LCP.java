class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] res = new char[n];
        char c = 'a';
        
        for (int i = 0; i < n; i++) {
            if (res[i] != 0) continue;
            if (c > 'z') return "";
            for (int j = i; j < n; j++) {
                if (lcp[i][j] > 0) {
                    res[j] = c;
                }
            }
            c++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = 0;
                if (res[i] == res[j]) {
                    val = (i + 1 < n && j + 1 < n) ? lcp[i + 1][j + 1] + 1 : 1;
                }
                if (lcp[i][j] != val) return "";
            }
        }
        
        return new String(res);
    }
}

