class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Check if rec1 is to the left of rec2
        // Check if rec1 is to the right of rec2
        // Check if rec1 is below rec2
        // Check if rec1 is above rec2
        return !(rec1[2] <= rec2[0] || // left
                 rec1[0] >= rec2[2] || // right
                 rec1[3] <= rec2[1] || // bottom
                 rec1[1] >= rec2[3]);  // top
    }
}
