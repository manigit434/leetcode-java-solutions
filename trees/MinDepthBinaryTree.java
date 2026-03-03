/*
Problem: Minimum Depth of Binary Tree
Link: https://leetcode.com/problems/minimum-depth-of-binary-tree/
Difficulty: Easy
Topic: Tree / Binary Tree
Approach: Recursive DFS to find minimum depth
Time Complexity: O(n) - each node visited once
Space Complexity: O(h) - recursion stack, h = height of tree

Extended Description:
This solution finds the minimum depth of a binary tree using a recursive depth-first search approach. 
For each node, we explore its left and right subtrees and calculate their minimum depths. 
Special care is taken for nodes with only one child, since the minimum depth must reach a leaf. 
The algorithm efficiently computes the shortest path to a leaf node in O(n) time and uses O(h) space for the recursion stack. 
Comments and thought-process notes are included to make it easy for human readers and recruiters to understand the approach and reasoning.
*/

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0; // Empty tree has depth 0
        
        // Recursively find the min depth of left and right subtrees
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        
        // If one subtree is null, we must consider the non-null subtree
        if (root.left == null || root.right == null) {
            return 1 + Math.max(leftDepth, rightDepth);
        }
        
        // Both subtrees exist, take the smaller depth
        return 1 + Math.min(leftDepth, rightDepth);
    }
}

// Optional TreeNode class reference
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}
