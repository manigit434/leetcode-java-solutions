/*
Problem: Best Time to Buy and Sell Stock
Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
Difficulty: Easy

Topics: Array, Greedy

Approach:
We iterate through the price list while tracking the lowest price seen so far.
For each day, we calculate the profit we would get if we sold the stock that day.

If the current price is lower than our recorded minimum price,
we update the minimum (better day to buy).

Otherwise, we check the profit (current price - minPrice) and update
the maximum profit if it's larger than what we have already found.

The idea is simple:
Buy at the lowest price seen so far and sell later at the best possible price.

Time Complexity: O(n)
Space Complexity: O(1)

Note:
We only pass through the array once and keep track of two values:
- the minimum price so far
- the maximum profit so far
*/

class Solution {

    public int maxProfit(int[] prices) {

        // Minimum price seen so far (best day to buy)
        int minPrice = Integer.MAX_VALUE;

        // Maximum profit we can achieve
        int maxProfit = 0;

        for (int price : prices) {

            // If we find a lower price, update the buy price
            if (price < minPrice) {
                minPrice = price;
            } 
            // Otherwise calculate profit if we sell today
            else {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }

        return maxProfit;
    }
}
