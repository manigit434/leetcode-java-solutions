/* 
Problem: Fancy Sequence
Link: https://leetcode.com/problems/fancy-sequence/
Difficulty: Hard
Topic: Design, Lazy Updates, Prefix Multiplication/Add
Approach: 
We store the sequence and track cumulative multiplication and addition operations lazily. 
Each element remembers the state of the global multiplier/addend at the time of append. 
To get an element, we reverse the effect of operations applied after it using modular inverse.
Time/Space Complexity:
- append: O(1)
- addAll: O(1)
- multAll: O(1)
- getIndex: O(1) using modular inverse
Extended Description:
Instead of updating the entire sequence on addAll/multAll, we maintain two variables:
1. globalMul: cumulative multiplier
2. globalAdd: cumulative adder
Each appended value stores its multiplier/add state. When getIndex is called, 
we compute the current value using modular arithmetic and modular inverse to undo old operations.
*/

import java.util.*;

class Fancy {

    private static final int MOD = 1_000_000_007;
    private List<Long> vals;     // stored original values
    private List<Long> muls;     // multiplier at the time of append
    private List<Long> adds;     // adder at the time of append

    private long globalMul;
    private long globalAdd;

    public Fancy() {
        vals = new ArrayList<>();
        muls = new ArrayList<>();
        adds = new ArrayList<>();
        globalMul = 1;
        globalAdd = 0;
    }

    public void append(int val) {
        // Store value with current global state
        vals.add((long) val);
        muls.add(globalMul);
        adds.add(globalAdd);
    }

    public void addAll(int inc) {
        globalAdd = (globalAdd + inc) % MOD;
    }

    public void multAll(int m) {
        globalMul = (globalMul * m) % MOD;
        globalAdd = (globalAdd * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= vals.size()) return -1;

        long val = vals.get(idx);
        long valMul = muls.get(idx);
        long valAdd = adds.get(idx);

        // Current value: val * (globalMul / valMul) + (globalAdd - valAdd * (globalMul / valMul))
        long inv = modInverse(valMul, MOD); // modular inverse
        long curr = val * (globalMul * inv % MOD) % MOD;
        long addPart = (globalAdd - valAdd * (globalMul * inv % MOD) % MOD + MOD) % MOD;

        curr = (curr + addPart) % MOD;
        return (int) curr;
    }

    // Modular inverse using Fermat's Little Theorem
    private long modInverse(long x, int mod) {
        return pow(x, mod - 2, mod);
    }

    private long pow(long x, long n, int mod) {
        long res = 1;
        x = x % mod;
        while (n > 0) {
            if ((n & 1) == 1) res = (res * x) % mod;
            x = (x * x) % mod;
            n >>= 1;
        }
        return res;
    }
}
