/*
Problem: Path Sum (LeetCode #112)
Link: https://leetcode.com/problems/path-sum/
Difficulty: Easy
Topic: Binary Tree, DFS, Recursion
Approach: Top-Down DFS with Target Reduction
Time Complexity: O(n)
Space Complexity: O(h)  // h = height of tree (recursion stack)

Extended Description:
This solution performs a depth-first traversal of the binary tree.
At each node, we subtract the node’s value from the remaining target sum.
When we reach a leaf node, we check whether the remaining sum equals
the leaf’s value.

The idea is simple but powerful: instead of accumulating a running sum,
we reduce the target as we go down the tree. This keeps the logic clean
and avoids extra variables.

Since each node is visited once, the time complexity is O(n).
The recursion stack can go as deep as the tree height, leading to O(h) space usage.
*/

class Solution {

    public boolean hasPathSum(TreeNode root, int targetSum) {

        // Base case:
        // If the tree is empty, there cannot be any root-to-leaf path.
        if (root == null) {
            return false;
        }
        
        // If the current node is a leaf node,
        // check whether the remaining target sum equals this node's value.
        // "Root-to-leaf" is the key requirement in this problem.
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        
        // Subtract the current node's value from the target.
        // This represents the remaining sum required for the rest of the path.
        int remainingSum = targetSum - root.val;
        
        // Recursively check the left and right subtrees.
        // If either subtree returns true, a valid path exists.
        return hasPathSum(root.left, remainingSum) ||
               hasPathSum(root.right, remainingSum);
    }

    /*
     * Thought Process:
     * - The phrase "root-to-leaf" means we only validate the sum at leaf nodes.
     * - Instead of keeping a running total, reducing the target keeps code minimal.
     * - This is a classic top-down DFS template used in many tree path problems.
     */
}
