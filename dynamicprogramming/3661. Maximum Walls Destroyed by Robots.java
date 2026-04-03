import java.util.*;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        
        Map<Integer, Integer> distMap = new HashMap<>();
        for (int i = 0; i < n; i++) distMap.put(robots[i], distance[i]);
        
        Arrays.sort(robots);
        Arrays.sort(walls);
        
        int[] dpLeft = new int[n];
        int[] dpRight = new int[n];

        int r0 = robots[0];
        int d0 = distMap.get(r0);
        dpLeft[0] = countInRange(walls, r0 - d0, r0);
        
        int limit0 = (n > 1) ? robots[1] - 1 : Integer.MAX_VALUE;
        dpRight[0] = countInRange(walls, r0, Math.min(r0 + d0, limit0));

        for (int i = 1; i < n; i++) {
            int pos = robots[i];
            int dist = distMap.get(pos);
            int prevPos = robots[i - 1];
            int prevDist = distMap.get(prevPos);

            int leftRangeStart = Math.max(pos - dist, prevPos + 1);
            int wallsThisLeft = countInRange(walls, leftRangeStart, pos);

            int rightLimit = (i == n - 1) ? Integer.MAX_VALUE : robots[i + 1] - 1;
            int wallsThisRight = countInRange(walls, pos, Math.min(pos + dist, rightLimit));

            int totalInGap = countInRange(walls, prevPos, pos);

            int fromPrevLeft = dpLeft[i - 1] + wallsThisLeft;
        
            int wallsPrevRight = countInRange(walls, prevPos, Math.min(prevPos + prevDist, pos - 1));
            int fromPrevRight = dpRight[i - 1] - wallsPrevRight + Math.min(wallsThisLeft + wallsPrevRight, totalInGap);
            
            dpLeft[i] = Math.max(fromPrevLeft, fromPrevRight);
            dpRight[i] = Math.max(dpLeft[i - 1], dpRight[i - 1]) + wallsThisRight;
        }

        return Math.max(dpLeft[n - 1], dpRight[n - 1]);
    }

    private int countInRange(int[] arr, int L, int R) {
        if (L > R) return 0;
        int start = lowerBound(arr, L);
        int end = upperBound(arr, R);
        return Math.max(0, end - start);
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (arr[m] < target) l = m + 1; else r = m;
        }
        return l;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (arr[m] <= target) l = m + 1; else r = m;
        }
        return l;
    }
}
