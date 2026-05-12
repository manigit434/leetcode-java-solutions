import java.util.Arrays;

class Solution {
    public int minimumEffort(int[][] tasks) {
        // Sort tasks based on (minimum - actual) in descending order.
        // This greedy approach prioritizes tasks with higher margins (minimum - actual)
        // to be completed earlier when current energy is high, minimizing total needed.
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        
        int totalEnergy = 0;
        int currentEnergy = 0;
        
        for (int[] task : tasks) {
            int actual = task[0];
            int minimum = task[1];
            
            // If current energy is less than required, add the difference
            if (currentEnergy < minimum) {
                totalEnergy += (minimum - currentEnergy);
                currentEnergy = minimum;
            }
            // Perform the task
            currentEnergy -= actual;
        }
        
        return totalEnergy;
    }
}
// Time Complexity: O(N log N) due to sorting, where N is the number of tasks.
// Space Complexity: O(1) or O(log N) depending on the sorting algorithm implementation.
