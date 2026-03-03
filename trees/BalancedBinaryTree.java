/*
Problem: Balanced Binary Tree
Link: https://leetcode.com/problems/balanced-binary-tree/
Difficulty: Easy
Topic: Tree / Binary Tree
Approach: Bottom-up DFS to check balance and height
Time Complexity: O(n)
Space Complexity: O(h) - recursion stack
*/

class Solution {
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) return 0; // Base case: empty subtree
        
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1; // Left subtree not balanced
        
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1; // Right subtree not balanced
        
        if (Math.abs(leftHeight - rightHeight) > 1) return -1; // Current node not balanced
        
        return 1 + Math.max(leftHeight, rightHeight); // Height of current node
    }
}

// Optional: TreeNode class for reference
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}
