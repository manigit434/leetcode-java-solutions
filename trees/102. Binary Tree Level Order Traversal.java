import java.util.*;

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

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Edge Case: If the tree is empty
        if (root == null) {
            return result;
        }
        
        // Queue to hold nodes for BFS traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Number of nodes at the current level
            List<Integer> currentLevel = new ArrayList<>();
            
            // Process all nodes belonging to the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                
                // Add left child to queue if it exists
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                // Add right child to queue if it exists
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            
            // Add the completed level list to the final result
            result.add(currentLevel);
        }
        
        return result;
    }
}
