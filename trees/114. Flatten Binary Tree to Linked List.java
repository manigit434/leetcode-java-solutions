class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left != null) {
                // Find the rightmost node in the left subtree
                TreeNode predecessor = curr.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }

                // Connect the original right subtree after the left subtree's rightmost node
                predecessor.right = curr.right;

                // Move the left subtree to the right and clear left
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }
}
