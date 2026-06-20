import java.util.Arrays;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;
        if (m == 0) {
            return n - 1;
        }

        int[][] extended = new int[m + 2][2];
        extended[0] = new int[]{1, 0};
        for (int i = 0; i < m; i++) {
            extended[i + 1] = restrictions[i];
        }
        extended[m + 1] = new int[]{n, n - 1};

        Arrays.sort(extended, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 1; i < extended.length; i++) {
            extended[i][1] = Math.min(extended[i][1], extended[i - 1][1] + (extended[i][0] - extended[i - 1][0]));
        }

        for (int i = extended.length - 2; i >= 0; i--) {
            extended[i][1] = Math.min(extended[i][1], extended[i + 1][1] + (extended[i + 1][0] - extended[i][0]));
        }

        int max = 0;
        for (int i = 0; i < extended.length - 1; i++) {
            int id1 = extended[i][0];
            int h1 = extended[i][1];
            int id2 = extended[i + 1][0];
            int h2 = extended[i + 1][1];
            
            int peak = (h1 + h2 + id2 - id1) / 2;
            max = Math.max(max, peak);
        }

        return max;
    }
}
/*
 * Time Complexity: O(M log M) where M is the number of restrictions, due to sorting the restrictions array.
 * Space Complexity: O(M) to store the extended restrictions array.
 */
