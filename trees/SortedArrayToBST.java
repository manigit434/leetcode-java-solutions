/*
Problem: Convert Sorted Array to Binary Search Tree
Link: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
Difficulty: Easy
Topic: Tree / Binary Search Tree
Approach: Recursive Divide & Conquer to build height-balanced BST
Time Complexity: O(n)
Space Complexity: O(log n) - recursion stack
*/

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) return null; // Base case: empty subarray
        
        int mid = left + (right - left) / 2; // Middle element as root
        TreeNode node = new TreeNode(nums[mid]);
        
        node.left = buildBST(nums, left, mid - 1); // Build left subtree
        node.right = buildBST(nums, mid + 1, right); // Build right subtree
        
        return node;
    }
}

// Optional: TreeNode class for reference
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val) {
        this.val = val;
    }
}
