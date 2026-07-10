import java.util.*;

public class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        TreeSet<Integer> uniqueSet = new TreeSet<>();
        for (int num : nums) {
            uniqueSet.add(num);
        }

        int m = uniqueSet.size();
        int[] uniqueVals = new int[m];
        int idx = 0;
        for (int val : uniqueSet) {
            uniqueVals[idx++] = val;
        }

        Map<Integer, Integer> valToIdx = new HashMap<>();
        for (int i = 0; i < m; i++) {
            valToIdx.put(uniqueVals[i], i);
        }

        int[] jumpRight = new int[m];
        int r = 0;
        for (int l = 0; l < m; l++) {
            while (r < m && uniqueVals[r] - uniqueVals[l] <= maxDiff) {
                r++;
            }
            jumpRight[l] = r - 1;
        }

        int LOG = 18;
        int[][] up = new int[LOG][m];
        for (int i = 0; i < m; i++) {
            up[0][i] = jumpRight[i];
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < m; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        int numQueries = queries.length;
        int[] answer = new int[numQueries];

        for (int i = 0; i < numQueries; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            if (u == v) {
                answer[i] = 0;
                continue;
            }

            int valU = nums[u];
            int valV = nums[v];

            if (valU == valV) {
                answer[i] = 1;
                continue;
            }

            int startVal = Math.min(valU, valV);
            int endVal = Math.max(valU, valV);

            int startIdx = valToIdx.get(startVal);
            int endIdx = valToIdx.get(endVal);

            int steps = 0;
            int curr = startIdx;

            for (int j = LOG - 1; j >= 0; j--) {
                if (up[j][curr] < endIdx) {
                    steps += (1 << j);
                    curr = up[j][curr];
                }
            }

            if (up[0][curr] >= endIdx) {
                answer[i] = steps + 1;
            } else {
                answer[i] = -1;
            }
        }

        return answer
