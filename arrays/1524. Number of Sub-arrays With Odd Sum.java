class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod = 1_000_000_007;

        // Start with evenCount = 1 to account for an empty prefix sum of 0
        int evenCount = 1;
        int oddCount = 0;

        int prefixSum = 0;
        long totalOddSubarrays = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 0) {
                // An even prefix sum minus any previous odd prefix sum yields an odd subarray
                totalOddSubarrays += oddCount;
                evenCount++;
            } else {
                // An odd prefix sum minus any previous even prefix sum yields an odd subarray
                totalOddSubarrays += evenCount;
                oddCount++;
            }
        }

        return (int) (totalOddSubarrays % mod);
    }
}
