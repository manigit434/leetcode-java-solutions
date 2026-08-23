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
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return buildTrees(1, n);
    }

    private List<TreeNode> buildTrees(int start, int end) {
        List<TreeNode> allTrees = new ArrayList<>();
        
        // Base case: if start > end, no elements to form a tree.
        // Add null to the list to allow loop execution for child nodes.
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // Iterate through all values from start to end to treat each as the root
        for (int i = start; i <= end; i++) {
            // Generate all possible left subtrees
            List<TreeNode> leftTrees = buildTrees(start, i - 1);
            
            // Generate all possible right subtrees
            List<TreeNode> rightTrees = buildTrees(i + 1, end);

            // Connect the root 'i' with all combinations of left and right subtrees
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currentRoot = new TreeNode(i);
                    currentRoot.left = left;
                    currentRoot.right = right;
                    allTrees.add(currentRoot);
                }
            }
        }
        
        return allTrees;
    }
}
