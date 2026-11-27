class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 1500;
        int[] freq = new int[maxFactor + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count how many items have a factor divisible by f
        int[] multiples = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            for (int m = f; m <= maxFactor; m += f) {
                multiples[f] += freq[m];
            }
        }

        // dp[w] = max items obtainable with budget at most w
        int[] dp = new int[budget + 1];

        // Stage 1: 0/1 Knapsack for buying the first copy of each item (unlocks free copies)
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            int totalGain = 1 + (multiples[factor] - 1); // 1 purchased + free copies (excluding itself)

            for (int w = budget; w >= price; w--) {
                dp[w] = Math.max(dp[w], dp[w - price] + totalGain);
            }
        }

        // Stage 2: Unbounded Knapsack for any remaining budget (buy cheapest item repeatedly)
        for (int w = minPrice; w <= budget; w++) {
            dp[w] = Math.max(dp[w], dp[w - minPrice] + 1);
        }

        return dp[budget];
    }
}
