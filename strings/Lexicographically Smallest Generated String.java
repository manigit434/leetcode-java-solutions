import java.util.Arrays;

class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int L = n + m - 1;
        
        char[] word = new char[L];
        boolean[] locked = new boolean[L];
        
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (locked[i + j] && word[i + j] != str2.charAt(j)) {
                        return "";
                    }
                    word[i + j] = str2.charAt(j);
                    locked[i + j] = true;
                }
            }
        }
        
        for (int i = 0; i < L; i++) {
            if (!locked[i]) {
                word[i] = 'a';
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (word[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                
                if (match) {
                    boolean broken = false;
                    for (int j = m - 1; j >= 0; j--) {
                        int pos = i + j;
                        if (!locked[pos]) {
                            word[pos] = 'b';
                            broken = true;
                            break;
                        }
                    }
                    if (!broken) {
                        return "";
                    }
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            boolean match = true;
            for (int j = 0; j < m; j++) {
                if (word[i + j] != str2.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (str1.charAt(i) == 'T' && !match) return "";
            if (str1.charAt(i) == 'F' && match) return "";
        }
        
        return new String(word);
    }
}
