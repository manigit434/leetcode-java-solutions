/*
Problem: Swap Nodes in Pairs (LeetCode #24)
Link: https://leetcode.com/problems/swap-nodes-in-pairs/
Difficulty: Medium
Topic: Linked List, Iteration
Approach: Iterative two-pointer swap using dummy node
Time Complexity: O(n)
Space Complexity: O(1)

Extended Description:
This solution swaps every two adjacent nodes in a singly-linked list using iteration.
A dummy node is used at the start to simplify swapping the head node.
We maintain a pointer 'prev' that always points to the node before the current pair.

For each pair:
1. Identify the first and second nodes.
2. Reassign the next pointers to swap them.
3. Move 'prev' forward to the first node of the swapped pair (now second in position).

This continues until fewer than two nodes remain.

Key Insight:
Using a dummy node avoids special cases for head swaps.
Iteratively adjusting pointers is efficient and avoids recursion stack overhead.
*/

class Solution {

    public ListNode swapPairs(ListNode head) {

        // Dummy node to simplify head handling
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 'prev' tracks the node before the pair to swap
        ListNode prev = dummy;

        // Continue while there are at least two nodes to swap
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // Swapping the pair by reassigning pointers
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // Move prev forward to the next pair
            prev = first;
        }

        // Return the new head
        return dummy.next;
    }

    /*
     * - Dummy node is a standard trick for head modifications.
     * - Always update 'prev' after swapping to prevent pointer confusion.
     * - This pattern is common for pairwise or k-group linked list operations.
     */
}
