class Solution {
    public int maxProduct(int n) {
        String s = String.valueOf(n);
        int maxProd = 0;
        int len = s.length();
        
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int d1 = s.charAt(i) - '0';
                int d2 = s.charAt(j) - '0';
                maxProd = Math.max(maxProd, d1 * d2);
            }
        }
        
        return maxProd;
    }
}
