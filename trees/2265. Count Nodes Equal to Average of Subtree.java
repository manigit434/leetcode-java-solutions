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
    private int matchingNodesCount = 0;

    public int averageOfSubtree(TreeNode root) {
        matchingNodesCount = 0;
        calculateSubtreeStats(root);
        return matchingNodesCount;
    }

    // Helper method that returns an array: [sum of subtree, count of nodes in subtree]
    private int[] calculateSubtreeStats(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        // 1. Traverse left and right subtrees to get their sums and counts
        int[] leftStats = calculateSubtreeStats(node.left);
        int[] rightStats = calculateSubtreeStats(node.right);

        // 2. Accumulate values for the current node's subtree
        int currentSum = node.val + leftStats[0] + rightStats[0];
        int currentCount = 1 + leftStats[1] + rightStats[1];

        // 3. Compute integer division average and compare with node value
        if (currentSum / currentCount == node.val) {
            matchingNodesCount++;
        }

        // 4. Return current sum and count up to the parent
        return new int[]{currentSum, currentCount};
    }
}
