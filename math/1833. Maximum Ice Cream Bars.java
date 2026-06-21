class Solution {
    public int maxIceCream(int[] costs, int coins) {
        // Step 1: Create a frequency array to represent costs
        int[] count = new int[100001];
        for (int cost : costs) {
            count[cost]++;
        }
        
        int iceCreamCount = 0;
        
        // Step 2: Iterate through possible prices from 1 to 100,000
        for (int price = 1; price < count.length; price++) {
            if (count[price] > 0) {
                // Determine how many we can buy of the current price
                int canBuy = Math.min(count[price], coins / price);
                
                if (canBuy > 0) {
                    iceCreamCount += canBuy;
                    coins -= (long) canBuy * price;
                }
                
                // If we run out of coins, we can't afford any more expensive bars
                if (coins < price) {
                    break;
                }
            }
        }
        
        return iceCreamCount;
    }
}
