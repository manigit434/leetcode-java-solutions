class Solution {
    public boolean checkDivisibility(int n) {
        int temp = n;
        int digitSum = 0;
        int digitProduct = 1;

        // Extract digits one by one
        while (temp > 0) {
            int digit = temp % 10;
            digitSum += digit;
            digitProduct *= digit;
            temp /= 10;
        }

        int totalSum = digitSum + digitProduct;

        // Check divisibility (totalSum will always be > 0 since 1 <= n)
        return n % totalSum == 0;
    }
}
