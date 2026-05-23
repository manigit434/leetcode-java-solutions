class Solution {
    public int mirrorDistance(int n) {
        long original = n;
        long reversed = 0;
        long temp = n;
        
        // Reverse the digits of the integer
        while (temp > 0) {
            reversed = reversed * 10 + (temp % 10);
            temp /= 10;
        }
        
        // Calculate and return the absolute difference
        return (int) Math.abs(original - reversed);
    }
}
