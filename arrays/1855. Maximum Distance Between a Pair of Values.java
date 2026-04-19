class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int maxDist = 0;
        int i = 0;
        int j = 0;
        int n1 = nums1.length;
        int n2 = nums2.length;

        while (i < n1 && j < n2) {
            if (nums1[i] <= nums2[j]) {
                maxDist = Math.max(maxDist, j - i);
                j++;
            } else {
                i++;
            }
        }

        /*
         * Time Complexity: O(n + m), where n is the length of nums1 and m is the length of nums2.
         * Each pointer moves at most through the length of its respective array once.
         * 
         * Space Complexity: O(1), as no extra space is used regardless of input size.
         */
        return maxDist;
    }
}
