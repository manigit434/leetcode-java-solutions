class Solution {
    public long sumAndMultiply(int n) {
        // Handle the base case where n is 0
        if (n == 0) {
            return 0;
        }

        // Convert the number to a string to easily process individual characters
        String str = Integer.toString(n);
        StringBuilder sb = new StringBuilder();
        long sum = 0; // Use long for sum to avoid any overflow down the line

        // Iterate through each character of the string
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // Check if the character is a non-zero digit
            if (ch != '0') {
                sb.append(ch);
                sum += (ch - '0'); // Convert char to its integer value
            }
        }

        // If there were no non-zero digits, x = 0
        if (sb.length() == 0) {
            return 0;
        }

        // Parse the concatenated string as a long to handle large values safely
        long x = Long.parseLong(sb.toString());

        // Return the product as a long
        return x * sum;
    }
}
