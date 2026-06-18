class Solution {
    public double angleClock(int hour, int minutes) {
        // Handle the 12 o'clock edge case by resetting it to 0
        if (hour == 12) {
            hour = 0;
        }
        
        // Calculate the positions of both hands in degrees
        // The hour hand moves 30 degrees per hour plus 0.5 degrees per minute passed
        double hourAngle = (hour * 30.0) + (minutes * 0.5);
        
        // The minute hand moves exactly 6 degrees every single minute
        double minuteAngle = minutes * 6.0;
        
        // Find the absolute difference between the two positions
        double diff = Math.abs(hourAngle - minuteAngle);
        
        // We always want the smaller interior angle, so if it's over 180,
        // take the remaining slice of the 360-degree circle
        if (diff > 180.0) {
            return 360.0 - diff;
        }
        
        return diff;
    }
}
