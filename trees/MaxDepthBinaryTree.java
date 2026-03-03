/*
Problem: Maximum Depth of Binary Tree
Link: https://leetcode.com/problems/maximum-depth-of-binary-tree/
Difficulty: Easy
Topic: Tree / Binary Tree
Approach: Recursive DFS to calculate max depth
Time Complexity: O(n)
Space Complexity: O(h) - recursion stack
*/

class Solution {
    public int maxDepth(TreeNode root) {
        // Base case: empty tree
        if (root == null) return 0;
        
        // Recursive DFS: depth of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        // Max depth = 1 (current node) + max of left and right
        return 1 + Math.max(leftDepth, rightDepth);
    }
}
