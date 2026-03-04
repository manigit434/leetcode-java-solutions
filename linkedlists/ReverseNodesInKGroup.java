/*
Problem: Reverse Nodes in k-Group (LeetCode #25)
Link: https://leetcode.com/problems/reverse-nodes-in-k-group/
Difficulty: Hard
Topic: Linked List, Iteration
Approach: Iterative group reversal using dummy node
Time Complexity: O(n)
Space Complexity: O(1)

Extended Description:
This solution reverses nodes of a linked list in groups of size k.
A dummy node simplifies handling the head and edge cases.

Steps:
1. Use 'prevGroup' to track the node before the current group.
2. Identify the kth node in the current group. If fewer than k nodes remain, stop.
3. Reverse the current group in-place.
4. Connect the previous group to the newly reversed group.
5. Move 'prevGroup' to the end of the reversed group and repeat.

Key Insight:
- Reversing in-place avoids extra memory usage.
- The dummy node pattern helps connect groups cleanly.
- Carefully tracking pointers is critical to avoid losing parts of the list.
*/

class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        // Dummy node to handle head swaps easily
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroup = dummy;
        
        while (true) {
            ListNode kth = prevGroup;
            // Find the kth node in the current group
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) break; // Less than k nodes left
            
            ListNode groupStart = prevGroup.next;
            ListNode nextGroup = kth.next;
            
            // Reverse current group
            ListNode prev = nextGroup;
            ListNode curr = groupStart;
            while (curr != nextGroup) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }
            
            // Connect previous group to the reversed group
            prevGroup.next = kth;
            // Move prevGroup to the end of the reversed group
            prevGroup = groupStart;
        }
        
        return dummy.next;
    }

    /*
     * - The dummy node avoids edge case handling for head.
     * - Always track nextGroup to prevent losing remaining list.
     * - This iterative pattern is common for k-group linked list problems.
     */
}
