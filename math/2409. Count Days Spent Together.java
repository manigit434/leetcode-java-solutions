class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int startAlice = getDayOfYear(arriveAlice);
        int endAlice = getDayOfYear(leaveAlice);
        int startBob = getDayOfYear(arriveBob);
        int endBob = getDayOfYear(leaveBob);

        int startOverlap = Math.max(startAlice, startBob);
        int endOverlap = Math.min(endAlice, endBob);

        return Math.max(0, endOverlap - startOverlap + 1);
    }

    private int getDayOfYear(String date) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] parts = date.split("-");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);

        int dayOfYear = day;
        for (int i = 0; i < month - 1; i++) {
            dayOfYear += daysInMonths[i];
        }
        return dayOfYear;
    }
}
