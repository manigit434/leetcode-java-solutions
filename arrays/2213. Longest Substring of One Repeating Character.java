import java.util.List;
import java.util.ArrayList;

class Solution {
    // Defines the structure of each node in the Segment Tree
    static class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        char prefChar;
        char suffChar;

        // Constructor for convenience
        Node(int maxLen, int prefLen, int suffLen, char prefChar, char suffChar) {
            this.maxLen = maxLen;
            this.prefLen = prefLen;
            this.suffLen = suffLen;
            this.prefChar = prefChar;
            this.suffChar = suffChar;
        }
    }

    private Node[] tree;
    private int n;

    // Helper method to merge two children nodes into a parent node
    private Node merge(Node left, Node right, int leftLen, int rightLen) {
        // Inherit outer boundary characters
        char prefChar = left.prefChar;
        char suffChar = right.suffChar;

        // Base max length from the best individual child
        int maxLen = Math.max(left.maxLen, right.maxLen);

        // Calculate prefix length
        int prefLen = left.prefLen;
        if (left.prefLen == leftLen && left.prefChar == right.prefChar) {
            prefLen = left.prefLen + right.prefLen;
        }

        // Calculate suffix length
        int suffLen = right.suffLen;
        if (right.suffLen == rightLen && right.suffChar == left.suffChar) {
            suffLen = right.suffLen + left.suffLen;
        }

        // If characters match across the middle seam, check the combined length
        if (left.suffChar == right.prefChar) {
            maxLen = Math.max(maxLen, left.suffLen + right.prefLen);
        }

        return new Node(maxLen, prefLen, suffLen, prefChar, suffChar);
    }

    private void build(String s, int node, int start, int end) {
        if (start == end) {
            char c = s.charAt(start);
            tree[node] = new Node(1, 1, 1, c, c);
            return;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        build(s, leftChild, start, mid);
        build(s, rightChild, mid + 1, end);

        tree[node] = merge(tree[leftChild], tree[rightChild], mid - start + 1, end - mid);
    }

    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            tree[node] = new Node(1, 1, 1, c, c);
            return;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;

        if (idx <= mid) {
            update(leftChild, start, mid, idx, c);
        } else {
            update(rightChild, mid + 1, end, idx, c);
        }

        tree[node] = merge(tree[leftChild], tree[rightChild], mid - start + 1, end - mid);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        this.n = s.length();
        this.tree = new Node[4 * n];
        
        // Build the initial segment tree
        build(s, 0, 0, n - 1);

        int k = queryIndices.length;
        int[] result = new int[k];

        // Process each query
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            
            // Perform dynamic update
            update(0, 0, n - 1, idx, c);
            
            // The root node (index 0) always holds the maximum for the whole string
            result[i] = tree[0].maxLen;
        }

        return result;
    }
}
