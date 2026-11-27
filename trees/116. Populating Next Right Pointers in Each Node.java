class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node leftmost = root;

        // Loop through each level until we reach the leaf level
        while (leftmost.left != null) {
            Node head = leftmost;

            while (head != null) {
                // Connection 1: Connect left child to right child of the same parent
                head.left.next = head.right;

                // Connection 2: Connect right child to the next parent's left child
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                // Move across the current level using already-populated next pointers
                head = head.next;
            }

            // Move down to the start of the next level
            leftmost = leftmost.left;
        }

        return root;
    }
}
