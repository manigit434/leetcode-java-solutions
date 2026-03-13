class Solution {
    // Time: O(n log M), Space: O(1)
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long minTime = Long.MAX_VALUE;
        for (int t : workerTimes) minTime = Math.min(minTime, t);

        long left = 0;
        long right = minTime * (long) mountainHeight * (mountainHeight + 1) / 2;

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (canReduce(mid, mountainHeight, workerTimes)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private boolean canReduce(long time, int height, int[] workers) {
        long total = 0;

        for (int t : workers) {
            double val = (Math.sqrt(1.0 + 8.0 * time / t) - 1) / 2;
            total += (long) val;
            if (total >= height) return true;
        }

        return total >= height;
    }
}
