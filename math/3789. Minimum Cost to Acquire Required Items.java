class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Option 1: Buy type 1 and type 2 items completely separately
        long separateCost = (long) need1 * cost1 + (long) need2 * cost2;

        // Option 2: Use type 3 items for the overlapping need, then buy the rest separately
        int commonNeed = Math.min(need1, need2);
        long mixedCost = (long) commonNeed * costBoth
                       + (long) (need1 - commonNeed) * cost1
                       + (long) (need2 - commonNeed) * cost2;

        // Option 3: Use type 3 items for everything (even if it overshoots the smaller need)
        int maxNeed = Math.max(need1, need2);
        long allBothCost = (long) maxNeed * costBoth;

        return Math.min(separateCost, Math.min(mixedCost, allBothCost));
    }
}
