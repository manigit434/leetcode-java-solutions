// Time: O(m+n), Space: O(1)
class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode a = headA, b = headB;

        // Traverse both lists, switch heads when reaching the end
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }

        return a; // null if no intersection or the intersection node
    }
}
