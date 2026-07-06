import java.util.Arrays;

public class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort by start point ascending. If starts are equal, sort by end point descending.
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int remainingCount = 0;
        int maxEnd = 0;

        // Iterate through intervals to filter out covered ones
        for (int[] interval : intervals) {
            int currentEnd = interval[1];
            
            // If current end extends past maxEnd, it's not covered
            if (currentEnd > maxEnd) {
                remainingCount++;
                maxEnd = currentEnd;
            }
        }

        return remainingCount;
    }
}
