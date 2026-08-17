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
    public ListNode deleteDuplicates(ListNode head) {
        // Dummy node to easily handle head deletions
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        
        while (head != null) {
            // Check if there is a sublist of duplicates starting at head
            if (head.next != null && head.val == head.next.val) {
                // Move head to the end of the duplicate sequence
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // Skip all duplicates by linking prev directly to the node after duplicates
                prev.next = head.next;
            } else {
                // No duplicate detected, safely move prev forward
                prev = prev.next;
            }
            // Move head forward for the next iteration
            head = head.next;
        }
        
        return dummy.next;
    }
}
