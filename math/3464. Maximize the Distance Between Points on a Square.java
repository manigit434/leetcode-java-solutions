import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        List<Long> positions = new ArrayList<>();

        for (int[] point : points) {
            int x = point[0];
            int y = point[1];

            if (x == 0) {
                positions.add((long) y);
            } else if (y == side) {
                positions.add((long) side + x);
            } else if (x == side) {
                positions.add(3L * side - y);
            } else {
                positions.add(4L * side - x);
            }
        }

        Collections.sort(positions);

        int answer = 0;
        long left = 1;
        long right = side;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (canPick(positions, side, k, mid)) {
                answer = (int) mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean canPick(List<Long> positions, int side, int k, long limit) {
        long perimeter = 4L * side;

        for (long start : positions) {
            long lastAllowed = start + perimeter - limit;
            long current = start;
            boolean possible = true;

            for (int picked = 1; picked < k; picked++) {
                int next = lowerBound(positions, current + limit);

                if (next == positions.size() || positions.get(next) > lastAllowed) {
                    possible = false;
                    break;
                }

                current = positions.get(next);
            }

            if (possible) {
                return true;
            }
        }

        return false;
    }

    private int lowerBound(List<Long> positions, long target) {
        int left = 0;
        int right = positions.size();

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (positions.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
