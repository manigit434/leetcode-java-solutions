class Solution {
    public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        int n = s.length();
        int[] leftBound = new int[n];
        int zeroCount = 0, oneCount = 0, left = 0;

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '0') zeroCount++;
            else oneCount++;
            while (zeroCount > k && oneCount > k) {
                if (s.charAt(left++) == '0') zeroCount--;
                else oneCount--;
            }
            leftBound[right] = left;
        }

        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + (i - leftBound[i] + 1);
        }

        long[] results = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int ql = queries[i][0], qr = queries[i][1];
            int low = ql, high = qr, pivot = qr + 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (leftBound[mid] >= ql) {
                    pivot = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            long triangleLen = pivot - ql;
            results[i] = (triangleLen * (triangleLen + 1) / 2) + (prefixSum[qr + 1] - prefixSum[pivot]);
        }
        return results;
    }
}
// Time Complexity: O(n + q log n)
// Space Complexity: O(n)
