import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        
        // Element class to store the original value and its initial index
        class Element {
            int val;
            int index;
            int digitSum;

            Element(int val, int index) {
                this.val = val;
                this.index = index;
                this.digitSum = getDigitSum(val);
            }
        }

        // Create an array of Elements
        Element[] arr = new Element[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Element(nums[i], i);
        }

        // Sort based on digit sum, then by value if digit sums are equal
        Arrays.sort(arr, (a, b) -> {
            if (a.digitSum != b.digitSum) {
                return Integer.compare(a.digitSum, b.digitSum);
            }
            return Integer.compare(a.val, b.val);
        });

        // Find the minimum number of swaps using graph cycle decomposition
        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            // If already visited or already in the correct position, skip
            if (visited[i] || arr[i].index == i) {
                continue;
            }

            // Find the cycle size
            int cycleSize = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j].index; // Move to the next index in the cycle
                cycleSize++;
            }

            // Update swaps count
            if (cycleSize > 0) {
                swaps += (cycleSize - 1);
            }
        }

        return swaps;
    }

    // Helper method to compute the sum of digits of a number
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
