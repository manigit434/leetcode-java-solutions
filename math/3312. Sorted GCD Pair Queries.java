import java.util.Arrays;

public class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // Step 1: Find the maximum value in nums to define bounds
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        // Step 2: Count the frequency of each number in the input array
        int[] counts = new int[maxVal + 1];
        for (int num : nums) {
            counts[num]++;
        }

        // Step 3: Compute total numbers that are multiples of each value 'i'
        long[] gcdCount = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long multiples = 0;
            for (int j = i; j <= maxVal; j += i) {
                multiples += counts[j];
            }
            
            // Total possible pairs choosing from multiples of 'i'
            long totalPairs = (multiples * (multiples - 1)) / 2;
            
            // Subtract pairs where the actual GCD is a strict multiple of 'i'
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairs -= gcdCount[j];
            }
            gcdCount[i] = totalPairs;
        }

        // Step 4: Build a prefix sum array of GCD counts
        long[] prefixSum = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSum[i] = prefixSum[i - 1] + gcdCount[i];
        }

        // Step 5: Answer each query using binary search (upper bound style)
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i];
            answer[i] = binarySearch(prefixSum, target);
        }

        return answer;
    }

    private int binarySearch(long[] prefixSum, long target) {
        int low = 1;
        int high = prefixSum.length - 1;
        int result = high;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (prefixSum[mid] > target) {
                result = mid; // Potential answer, look for smaller valid index
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}
