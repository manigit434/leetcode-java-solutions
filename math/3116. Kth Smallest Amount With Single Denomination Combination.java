class Solution {
    private int[] coins;
    private int targetK;

    public long findKthSmallest(int[] coins, int k) {
        this.coins = coins;
        this.targetK = k;
        long left = 1;
        long right = 2L * k * 25; // Upper bound limit
        
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (countValues(mid) >= targetK) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private long countValues(long mx) {
        long count = 0;
        int n = coins.length;
        // Iterate through all subsets using bitmask
        for (int i = 1; i < (1 << n); ++i) {
            long lcmVal = 1;
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    lcmVal = lcm(lcmVal, coins[j]);
                    if (lcmVal > mx) {
                        break;
                    }
                }
            }
            if (lcmVal <= mx) {
                int setBits = Integer.bitCount(i);
                if (setBits % 2 == 1) {
                    count += mx / lcmVal;
                } else {
                    count -= mx / lcmVal;
                }
            }
        }
        return count;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
