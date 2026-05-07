// Time Complexity: O(1) - specifically O(number of set bits)
// Space Complexity: O(1)
public class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
