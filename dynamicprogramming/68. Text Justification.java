import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int lineLength = words[i].length();
            int last = i + 1;
            while (last < words.length) {
                if (lineLength + 1 + words[last].length() > maxWidth) break;
                lineLength += 1 + words[last].length();
                last++;
            }

            StringBuilder sb = new StringBuilder();
            int count = last - i;
            if (last == words.length || count == 1) {
                for (int j = i; j < last; j++) {
                    sb.append(words[j]);
                    if (j != last - 1) sb.append(" ");
                }
                while (sb.length() < maxWidth) sb.append(" ");
            } else {
                int spaces = (maxWidth - (lineLength - (count - 1)));
                int gap = spaces / (count - 1);
                int extra = spaces % (count - 1);

                for (int j = i; j < last; j++) {
                    sb.append(words[j]);
                    if (j != last - 1) {
                        for (int k = 0; k <= (gap + (j - i < extra ? 1 : 0) - 1); k++) {
                            sb.append(" ");
                        }
                    }
                }
            }
            res.add(sb.toString());
            i = last;
        }
        return res;
    }
}

// Time Complexity: O(n), where n is the total number of characters in the input words.
// Space Complexity: O(n) to store the result.
