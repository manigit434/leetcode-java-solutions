/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // Base case: we need at least 3 nodes to have a critical point
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int minDistance = Integer.MAX_VALUE;
        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 1; // 0-indexed tracking

        while (curr.next != null) {
            ListNode nextNode = curr.next;
            
            // Check if curr is a local maxima or local minima
            boolean isMaxima = curr.val > prev.val && curr.val > nextNode.val;
            boolean isMinima = curr.val < prev.val && curr.val < nextNode.val;

            if (isMaxima || isMinima) {
                // If this is the first critical point found
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    // Update the minimum distance between adjacent critical points
                    minDistance = Math.min(minDistance, currentIndex - prevCriticalIndex);
                }
                // Track the most recent critical point
                prevCriticalIndex = currentIndex;
            }

            // Move pointers forward
            prev = curr;
            curr = nextNode;
            currentIndex++;
        }

        // If fewer than two critical points were found
        if (firstCriticalIndex == prevCriticalIndex) {
            return new int[]{-1, -1};
        }

        // Maximum distance is always between the very first and very last critical point
        int maxDistance = prevCriticalIndex - firstCriticalIndex;

        return new int[]{minDistance, maxDistance};
    }
}
