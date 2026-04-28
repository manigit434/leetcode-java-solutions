class Solution {
    public int minimumOR(int[][] grid) {
        int maxVal = 0;
        for (int[] row : grid) {
            for (int x : row) {
                maxVal = Math.max(maxVal, x);
            }
        }
        
        int maxBits = 32 - Integer.numberOfLeadingZeros(maxVal);
        int ans = 0;

        // Greedy approach: try to make each bit 0 starting from MSB
        for (int i = maxBits - 1; i >= 0; i--) {
            // mask represents bits that are "allowed" to be 0
            int mask = ans | ((1 << i) - 1);
            boolean possible = true;

            for (int[] row : grid) {
                boolean foundInRow = false;
                for (int x : row) {
                    // Check if x can fit within our current bits target
                    if ((x | mask) == mask) {
                        foundInRow = true;
                        break;
                    }
                }
                if (!foundInRow) {
                    possible = false;
                    break;
                }
            }

            // If we couldn't find a valid number in every row for this bit to be 0
            if (!possible) {
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
// Time Complexity: O(M * N * Log(MaxVal))
// Space Complexity: O(1)
