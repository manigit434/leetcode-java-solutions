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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private ListNode head;

    public TreeNode sortedListToBST(ListNode head) {
        this.head = head;
        
        // Find the total size of the linked list
        int size = 0;
        ListNode curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        
        // Recursively build the balanced BST
        return convertListToBST(0, size - 1);
    }

    private TreeNode convertListToBST(int left, int right) {
        // Base case
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;

        // 1. Recursively build the left subtree
        TreeNode leftChild = convertListToBST(left, mid - 1);

        // 2. Process the current root node using the current head element
        TreeNode root = new TreeNode(this.head.val);
        root.left = leftChild;

        // Move the pointer to the next element in the linked list
        this.head = this.head.next;

        // 3. Recursively build the right subtree
        root.right = convertListToBST(mid + 1, right);

        return root;
    }
}
