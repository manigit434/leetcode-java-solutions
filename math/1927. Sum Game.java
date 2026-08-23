class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftSum = 0, rightSum = 0;
        int leftQuestion = 0, rightQuestion = 0;

        // Process the first half
        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                leftQuestion++;
            } else {
                leftSum += c - '0';
            }
        }

        // Process the second half
        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                rightQuestion++;
            } else {
                rightSum += c - '0';
            }
        }

        // If total '?' count is odd, Alice always wins 
        // because she gets the last move to disrupt any balance.
        if ((leftQuestion + rightQuestion) % 2 != 0) {
            return true;
        }

        // Calculate differences
        int sumDiff = leftSum - rightSum;
        int questionDiff = rightQuestion - leftQuestion;

        // Bob wins ONLY if the side with fewer questions has a larger sum,
        // and that sum deficit can be exactly covered by pairs of '?' (each pair = 9)
        return !(sumDiff == (questionDiff / 2) * 9);
    }
}
