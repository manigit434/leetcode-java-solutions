import java.util.ArrayList;
import java.util.List;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        dfs(root, targetSum, currentPath, result);
        return result;
    }

    private void dfs(TreeNode node, int remainingSum, List<Integer> currentPath, List<List<Integer>> result) {
        // Base case: if the node is null, return
        if (node == null) {
            return;
        }

        // Include the current node in the path
        currentPath.add(node.val);

        // Check if the current node is a leaf and its value equals the remaining sum
        if (node.left == null && node.right == null && node.val == remainingSum) {
            // We must create a new list copy because currentPath is modified continuously
            result.add(new ArrayList<>(currentPath));
        } else {
            // Otherwise, continue to explore left and right subtrees
            dfs(node.left, remainingSum - node.val, currentPath, result);
            dfs(node.right, remainingSum - node.val, currentPath, result);
        }

        // Backtrack: remove the current node from the path before returning to the parent
        currentPath.remove(currentPath.size() - 1);
    }
}
