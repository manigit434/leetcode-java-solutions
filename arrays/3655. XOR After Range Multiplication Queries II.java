import java.util.*;

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        long MOD = 1000000007L;
        int B = 65;
        
        List<int[]>[] smallKQueries = new List[B + 1];
        
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            if (v == 1) continue;
            
            if (k > B) {
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) (((long) nums[i] * v) % MOD);
                }
            } else {
                if (smallKQueries[k] == null) smallKQueries[k] = new ArrayList<>();
                smallKQueries[k].add(q);
            }
        }

        for (int k = 1; k <= B; k++) {
            if (smallKQueries[k] == null) continue;
            
            for (int r = 0; r < k; r++) {
                int maxIdx = (n - 1 - r) / k;
                if (maxIdx < 0) continue;
                
                long[] diff = new long[maxIdx + 2];
                Arrays.fill(diff, 1L);
                boolean applied = false;
                
                for (int[] q : smallKQueries[k]) {
                    int ql = q[0], qr = q[1], qv = q[3];
                    if (ql % k == r) {
                        int start = ql / k;
                        int end = (qr - r) / k;
                        if (start <= end) {
                            applied = true;
                            diff[start] = (diff[start] * qv) % MOD;
                            diff[end + 1] = (diff[end + 1] * power(qv, MOD - 2, MOD)) % MOD;
                        }
                    }
                }
                
                if (applied) {
                    long curr = 1;
                    for (int i = 0; i <= maxIdx; i++) {
                        curr = (curr * diff[i]) % MOD;
                        int actualIdx = i * k + r;
                        nums[actualIdx] = (int) (((long) nums[actualIdx] * curr) % MOD);
                    }
                }
            }
        }

        int res = 0;
        for (int x : nums) res ^= x;
        return res;
    }

    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
}
