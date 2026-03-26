import java.util.*;

public class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long total = 0;
        long[] rowSum = new long[m];
        long[] colSum = new long[n];
        HashMap<Integer, int[]> pos = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                total += v;
                rowSum[i] += v;
                colSum[j] += v;
                int[] a = pos.get(v);
                if (a == null) pos.put(v, new int[]{i, i, j, j});
                else {
                    if (i < a[0]) a[0] = i;
                    if (i > a[1]) a[1] = i;
                    if (j < a[2]) a[2] = j;
                    if (j > a[3]) a[3] = j;
                }
            }
        }
        int v00 = grid[0][0];
        int v0n = grid[0][n - 1];
        int vm0 = grid[m - 1][0];
        int vmn = grid[m - 1][n - 1];
        long[] prefRow = new long[m + 1];
        for (int i = 0; i < m; i++) prefRow[i + 1] = prefRow[i] + rowSum[i];
        long[] prefCol = new long[n + 1];
        for (int j = 0; j < n; j++) prefCol[j + 1] = prefCol[j] + colSum[j];
        for (int r = 0; r <= m - 2; r++) {
            long top = prefRow[r + 1];
            long bottom = total - top;
            if (top == bottom) return true;
            if (top > bottom) {
                long diff = top - bottom;
                if (diff <= 100000) {
                    int d = (int) diff;
                    int[] a = pos.get(d);
                    if (a != null) {
                        int topHeight = r + 1;
                        if (topHeight >= 2) {
                            if (n == 1) {
                                if (a[0] <= r && (a[0] == 0 || a[1] == r)) return true;
                            } else {
                                if (a[0] <= r) return true;
                            }
                        } else {
                            if (v00 == d || v0n == d) return true;
                        }
                    }
                }
            } else {
                long diff = bottom - top;
                if (diff <= 100000) {
                    int d = (int) diff;
                    int[] a = pos.get(d);
                    if (a != null) {
                        int bottomHeight = m - (r + 1);
                        if (bottomHeight >= 2) {
                            if (n == 1) {
                                if (a[1] >= r + 1 && (a[0] == r + 1 || a[1] == m - 1)) return true;
                            } else {
                                if (a[1] >= r + 1) return true;
                            }
                        } else {
                            if (vm0 == d || vmn == d) return true;
                        }
                    }
                }
            }
        }
        for (int c = 0; c <= n - 2; c++) {
            long left = prefCol[c + 1];
            long right = total - left;
            if (left == right) return true;
            if (left > right) {
                long diff = left - right;
                if (diff <= 100000) {
                    int d = (int) diff;
                    int[] a = pos.get(d);
                    if (a != null) {
                        int leftWidth = c + 1;
                        if (leftWidth >= 2) {
                            if (m == 1) {
                                if (a[2] <= c && (a[2] == 0 || a[3] == c)) return true;
                            } else {
                                if (a[2] <= c) return true;
                            }
                        } else {
                            if (v00 == d || vm0 == d) return true;
                        }
                    }
                }
            } else {
                long diff = right - left;
                if (diff <= 100000) {
                    int d = (int) diff;
                    int[] a = pos.get(d);
                    if (a != null) {
                        int rightWidth = n - (c + 1);
                        if (rightWidth >= 2) {
                            if (m == 1) {
                                if (a[2] >= c + 1 && (a[2] == c + 1 || a[3] == n - 1)) return true;
                            } else {
                                if (a[3] >= c + 1) return true;
                            }
                        } else {
                            if (v0n == d || vmn == d) return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
