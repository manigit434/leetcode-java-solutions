class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        int N = n + k - 1;
        int K = 2 * k;
        
        // If we need to choose more points than available
        if (K > N) return 0;
        
        // Compute C(N, K) % MOD
        // Optimize by using C(N, K) = C(N, N - K) if K > N / 2
        if (K > N - K) {
            K = N - K;
        }
        
        long numerator = 1;
        long denominator = 1;
        
        for (int i = 0; i < K; i++) {
            numerator = (numerator * (N - i)) % MOD;
            denominator = (denominator * (i + 1)) % MOD;
        }
        
        // Result = (numerator * modularInverse(denominator)) % MOD
        return (int) ((numerator * modInverse(denominator, MOD)) % MOD);
    }
    
    // Fermat's Little Theorem for Modular Inverse
    private long modInverse(long n, long m) {
        return power(n, m - 2, m);
    }
    
    // Fast exponentiation: (base^exp) % mod
    private long power(long base, long exp, long mod) {
        long res = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}
