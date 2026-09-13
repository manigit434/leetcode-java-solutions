class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxOverlap = 0;

        // Try every possible shift combination (rowShift, colShift)
        // Shifting img1 relative to img2
        for (int rowShift = -n + 1; rowShift < n; rowShift++) {
            for (int colShift = -n + 1; colShift < n; colShift++) {
                maxOverlap = Math.max(maxOverlap, countOverlap(img1, img2, rowShift, colShift, n));
            }
        }

        return maxOverlap;
    }

    private int countOverlap(int[][] img1, int[][] img2, int rowShift, int colShift, int n) {
        int count = 0;

        // Iterate through all cells of img1
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                // Calculate the corresponding target position in img2
                int targetR = r + rowShift;
                int targetC = c + colShift;

                // Check if the target position is within the boundaries of img2
                if (targetR >= 0 && targetR < n && targetC >= 0 && targetC < n) {
                    // If both positions contain a 1, increment the count
                    if (img1[r][c] == 1 && img2[targetR][targetC] == 1) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
