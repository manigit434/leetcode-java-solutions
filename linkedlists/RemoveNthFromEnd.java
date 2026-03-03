/*
Problem: Remove Nth Node From End of List
Link: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
Difficulty: Medium
Topic: Linked List / Two Pointers
Approach: One-pass using fast and slow pointers with a dummy node
Time Complexity: O(n)
Space Complexity: O(1)

Extended Description:
This solution removes the nth node from the end of a singly linked list in a single traversal using two pointers. 
A dummy node is added at the start to handle edge cases such as removing the first node. 
The fast pointer moves n steps ahead, then both fast and slow pointers move together until fast reaches the end. 
Slow then points to the node before the target, which can be removed by adjusting pointers. 
This approach demonstrates mastery of linked list traversal, pointer manipulation, and edge-case handling, commonly asked in technical interviews.
*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node to simplify edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast pointer n+1 steps ahead to maintain the gap
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Skip the target node
        slow.next = slow.next.next;

        return dummy.next;
    }
}
