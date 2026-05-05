class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // Base case: empty list, single node, or no rotation needed
        if (head == null || head.next == null || k == 0) return head;

        // 1. Get length and find the tail (recursive)
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // 2. Adjust k (handles cases where k > length)
        k = k % length;
        if (k == 0) return head;

        // 3. Connect tail to head to temporarily make it a circle
        tail.next = head;

        // 4. Find the new break point recursively
        return findNewHead(head, length - k);
    }

    private ListNode findNewHead(ListNode current, int stepsToNewHead) {
        // We move to the node right BEFORE the new head
        if (stepsToNewHead == 1) {
            ListNode newHead = current.next;
            current.next = null; // Break the circular connection
            return newHead;
        }
        return findNewHead(current.next, stepsToNewHead - 1);
    }
    // Time complexity: O(n)
    // Space complexity: O(n) due to recursion stack
}
