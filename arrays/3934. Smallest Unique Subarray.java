import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 131_542_391L;
    private static final long BASE2 = 265_443_576L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int left = 1, right = n;
        int ans = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;
        long hash1 = 0, hash2 = 0;
        long pow1 = 1, pow2 = 1;

        for (int i = 0; i < len; i++) {
            hash1 = (hash1 * BASE1 + nums[i]) % MOD1;
            hash2 = (hash2 * BASE2 + nums[i]) % MOD2;
            if (i < len - 1) {
                pow1 = (pow1 * BASE1) % MOD1;
                pow2 = (pow2 * BASE2) % MOD2;
            }
        }

        // Pack both 32-bit hashes into a single 64-bit Long key to avoid collisions
        Map<Long, Integer> freq = new HashMap<>(n - len + 1);
        long initialKey = (hash1 << 32) | (hash2 & 0xFFFFFFFFL);
        freq.put(initialKey, 1);

        for (int i = len; i < n; i++) {
            int outVal = nums[i - len];
            int inVal = nums[i];

            hash1 = ((hash1 - outVal * pow1) % MOD1 + MOD1) % MOD1;
            hash1 = (hash1 * BASE1 + inVal) % MOD1;

            hash2 = ((hash2 - outVal * pow2) % MOD2 + MOD2) % MOD2;
            hash2 = (hash2 * BASE2 + inVal) % MOD2;

            long key = (hash1 << 32) | (hash2 & 0xFFFFFFFFL);
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
