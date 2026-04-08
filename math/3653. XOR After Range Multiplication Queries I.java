class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        long mod = 1_000_000_007L;

        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            long v = q[3];

            for (int i = l; i <= r; i += k) {
                nums[i] = (int) ((nums[i] * v) % mod);
            }
        }

        int res = 0;
        for (int x : nums) {
            res ^= x;
        }
        return res;
    }
}
