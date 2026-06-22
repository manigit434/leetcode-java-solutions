class Solution {
    public int maxNumberOfBalloons(String text) {
        int b = 0, a = 0, l = 0, o = 0, n = 0;
        
        // Step 1: Count frequencies of required characters
        for (char ch : text.toCharArray()) {
            switch (ch) {
                case 'b': b++; break;
                case 'a': a++; break;
                case 'l': l++; break;
                case 'o': o++; break;
                case 'n': n++; break;
            }
        }
        
        // Step 2 & 3: Divide double letters and find the minimum bottleneck
        l = l / 2;
        o = o / 2;
        
        return Math.min(b, Math.min(a, Math.min(l, Math.min(o, n))));
    }
}
