class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        if (n == 0) return "";
        
        int cols = n / rows;
        StringBuilder res = new StringBuilder();
        
        for (int c = 0; c < cols; c++) {
            for (int i = 0, j = c; i < rows && j < cols; i++, j++) {
                res.append(encodedText.charAt(i * cols + j));
            }
        }
        
        int last = res.length() - 1;
        while (last >= 0 && res.charAt(last) == ' ') {
            last--;
        }
        
        return res.substring(0, last + 1);
    }
}
