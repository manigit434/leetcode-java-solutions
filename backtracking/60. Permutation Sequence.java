import java.util.*;

class Solution {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        List<Integer> numbers = new ArrayList<>();
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
            numbers.add(i);
        }
        numbers.add(n);

        k--; // 0-based indexing
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factorial[i];
            sb.append(numbers.get(index));
            numbers.remove(index);
            if (i > 0) k %= factorial[i];
        }

        // Time Complexity: O(N^2) due to list removals
        // Space Complexity: O(N) to store factorials and numbers
        return sb.toString();
    }
}
