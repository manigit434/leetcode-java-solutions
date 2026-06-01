import java.util.Arrays;

class Solution {
    public int minimumCost(int[] cost) {
        // Sort in ascending order first
        Arrays.sort(cost);
        
        int totalCost = 0;
        int index = 0;
        
        // Iterate backwards from the most expensive candy
        for (int i = cost.length - 1; i >= 0; i--) {
            if (index % 3 != 2) {
                totalCost += cost[i];
            }
            index++;
        }
        
        return totalCost;
    }
}
