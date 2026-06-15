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
    public ListNode deleteMiddle(ListNode head) {
        // Edge case: If there's only one node, deleting it leaves an empty list
        if (head == null || head.next == null) {
            return null;
        }
        
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        
        // Move fast by 2 steps and slow by 1 step
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Delete the middle node by skipping it
        prev.next = slow.next;
        
        return head;
    }
}
