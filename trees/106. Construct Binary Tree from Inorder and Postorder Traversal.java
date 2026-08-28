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
    private Map<Integer, Integer> inorderMap;
    private int postorderIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        inorderMap = new HashMap<>();
        // Start from the last element of the postorder array
        postorderIndex = postorder.length - 1;

        // Map each value to its index in the inorder array for O(1) lookups
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTreeHelper(postorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] postorder, int left, int right) {
        // Base case: if there are no elements to form a subtree
        if (left > right) {
            return null;
        }

        // Pick the current root value using postorderIndex and decrement it
        int rootVal = postorder[postorderIndex--];
        TreeNode root = new TreeNode(rootVal);

        // Find where this root splits the inorder array
        int inorderRootIndex = inorderMap.get(rootVal);

        // Crucial: Build the RIGHT subtree first because postorder processed 
        // Left -> Right -> Root, meaning reading backwards goes Root -> Right -> Left
        root.right = buildTreeHelper(postorder, inorderRootIndex + 1, right);
        
        // Build the LEFT subtree
        root.left = buildTreeHelper(postorder, left, inorderRootIndex - 1);

        return root;
    }
}
