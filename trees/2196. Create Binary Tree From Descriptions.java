import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    public TreeNode createBinaryTree(int[][] descriptions) {
        // Map to store value to TreeNode reference mappings
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        // Set to store all node values that are children
        Set<Integer> childrenSet = new HashSet<>();
        
        for (int[] desc : descriptions) {
            int parentVal = desc[0];
            int childVal = desc[1];
            boolean isLeft = desc[2] == 1;
            
            // Retrieve or instantiate parent node
            TreeNode parentNode = nodeMap.computeIfAbsent(parentVal, TreeNode::new);
            // Retrieve or instantiate child node
            TreeNode childNode = nodeMap.computeIfAbsent(childVal, TreeNode::new);
            
            // Establish pointers
            if (isLeft) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
            
            // Mark the node value as a child
            childrenSet.add(childVal);
        }
        
        // Find the root (the only node value that is never a child)
        for (int parentVal : nodeMap.keySet()) {
            if (!childrenSet.contains(parentVal)) {
                return nodeMap.get(parentVal);
            }
        }
        
        return null;
    }
}
