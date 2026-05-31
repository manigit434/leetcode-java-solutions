class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        java.util.Arrays.sort(asteroids);
        long currentMass = mass;
        for (int asteroid : asteroids) {
            if (currentMass >= asteroid) {
                currentMass += asteroid;
            } else {
                return false;
            }
        }
        return true;
    }
}

// Time Complexity: O(N * log(N)) where N is the number of asteroids (due to sorting)
// Space Complexity: O(log(N)) depending on the underlying sorting algorithm implementation
