import java.util.Arrays;

class Solution {
    // Fenwick Tree (Binary Indexed Tree) implementation
    private static class BIT {
        int[] tree;
        int size;

        BIT(int size) {
            this.size = size;
            this.tree = new int[size + 1];
        }

        void update(int i, int delta) {
            while (i <= size) {
                tree[i] += delta;
                i += i & -i;
            }
        }

        int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        
        // Step 1: Transform to +1 / -1 and build prefix sums
        for (int i = 0; i < n; i++) {
            int val = (nums[i] == target) ? 1 : -1;
            prefix[i + 1] = prefix[i] + val;
        }

        // Step 2: Coordinate compression for prefix sum values
        int[] sortedUnique = prefix.clone();
        Arrays.sort(sortedUnique);
        int uniqueLen = uniqueLength(sortedUnique);

        // Step 3: Count valid pairs using the BIT
        BIT bit = new BIT(uniqueLen);
        long totalSubarrays = 0;

        for (int p : prefix) {
            int rank = getRank(sortedUnique, uniqueLen, p);
            // Count elements strictly smaller than the current rank
            totalSubarrays += bit.query(rank - 1);
            // Insert current prefix sum rank into the BIT
            bit.update(rank, 1);
        }

        return totalSubarrays;
    }

    private int uniqueLength(int[] arr) {
        if (arr.length == 0) return 0;
        int j = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[j]) {
                j++;
                arr[j] = arr[i];
            }
        }
        return j + 1;
    }

    private int getRank(int[] arr, int len, int val) {
        int low = 0, high = len - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] == val) return mid + 1; // 1-based index for BIT
            else if (arr[mid] < val) low = mid + 1;
            else high = mid - 1;
        }
        return low + 1;
    }
}
