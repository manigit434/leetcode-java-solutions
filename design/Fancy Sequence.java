import java.util.*;

class Fancy {
    private static final long MOD = 1_000_000_007L;

    private List<Long> seq = new ArrayList<>();
    private long mul = 1;
    private long add = 0;

    public Fancy() {}

    public void append(int val) {
        long x = (val - add + MOD) % MOD;
        x = x * modInverse(mul) % MOD;
        seq.add(x);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= seq.size()) return -1;
        long val = seq.get(idx);
        long res = (val * mul % MOD + add) % MOD;
        return (int) res;
    }

    private long modInverse(long x) {
        return modPow(x, MOD - 2);
    }

    private long modPow(long a, long b) {
        long res = 1;
        a %= MOD;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }
}

/*
Time: O(log MOD) per append (due to mod inverse), O(1) others
Space: O(n)
*/
