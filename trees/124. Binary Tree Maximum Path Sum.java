class Solution {
    // We use a class-level variable to track the overall maximum
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        calculateGain(root);
        return maxSum;
    }

    private int calculateGain(TreeNode node) {
        if (node == null) return 0;

        // Recursively find the max gain from left and right subtrees
        // We use Math.max(..., 0) because we can choose NOT to include a path if it's negative
        int leftGain = Math.max(calculateGain(node.left), 0);
        int rightGain = Math.max(calculateGain(node.right), 0);

        // This is the path that passes THROUGH the current node as the "highest" point (peak)
        int currentPathSum = node.val + leftGain + rightGain;

        // Update the global maximum if the current local path is better
        maxSum = Math.max(maxSum, currentPathSum);

        // Return the max gain this node can contribute to its parent (only one branch can be chosen)
        return node.val + Math.max(leftGain, rightGain);
    }
}

// Time Complexity: O(n) - We visit every node exactly once.
// Space Complexity: O(h) - Where h is the height of the tree, used by the recursion stack.
