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
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Integer low, Integer high) {
        // An empty tree is a valid BST
        if (node == null) {
            return true;
        }

        // The current node's value must be strictly within the low and high boundaries
        if ((low != null && node.val <= low) || (high != null && node.val >= high)) {
            return false;
        }

        // Recursively validate subtrees with updated strict boundaries
        return validate(node.left, low, node.val) && validate(node.right, node.val, high);
    }
}
