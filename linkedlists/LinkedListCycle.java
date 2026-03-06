/*
Problem: Linked List Cycle
Link: https://leetcode.com/problems/linked-list-cycle/
Difficulty: Easy
Topic: Linked List, Two Pointers
Approach: Floyd's Tortoise and Hare (Fast & Slow Pointers)

Time Complexity: O(n)
Space Complexity: O(1)

Extended Description:
This problem asks us to determine if a linked list contains a cycle. A cycle occurs
when a node points back to a previous node. The most efficient way to solve this
without extra space is using the fast and slow pointer technique, also called
Floyd's Tortoise and Hare algorithm.

- Slow pointer moves one step at a time.
- Fast pointer moves two steps at a time.
- If there is a cycle, fast will eventually meet slow inside the loop.
- If there is no cycle, fast will reach the end (null).

This is a classic pattern in linked list problems where detecting loops efficiently
without extra memory is required. The solution runs in O(n) time and O(1) space.
*/

class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
}
