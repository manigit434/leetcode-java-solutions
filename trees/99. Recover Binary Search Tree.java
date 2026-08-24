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
    public void recoverTree(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;
        
        TreeNode curr = root;
        
        while (curr != null) {
            if (curr.left == null) {
                // Process the current node
                if (prev != null && prev.val > curr.val) {
                    if (first == null) {
                        first = prev;
                    }
                    second = curr;
                }
                prev = curr;
                curr = curr.right;
            } else {
                // Find the inorder predecessor of curr
                TreeNode predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }
                
                if (predecessor.right == null) {
                    // Create a temporary link back to curr
                    predecessor.right = curr;
                    curr = curr.left;
                } else {
                    // Revert the changes made to the tree structure
                    predecessor.right = null;
                    
                    // Process the current node
                    if (prev != null && prev.val > curr.val) {
                        if (first == null) {
                            first = prev;
                        }
                        second = curr;
                    }
                    prev = curr;
                    curr = curr.right;
                }
            }
        }
        
        // Swap the values of the two misplaced nodes
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }
}
