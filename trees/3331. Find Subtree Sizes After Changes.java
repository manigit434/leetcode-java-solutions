import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        
        for (int i = 1; i < n; i++) {
            tree.get(parent[i]).add(i);
        }

        int[] newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Step 1: Find the closest matching ancestor for every node
        findNewParents(0, tree, s, lastSeen, newParent);

        // Step 2: Build the modified tree
        List<List<Integer>> newTree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            newTree.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            newTree.get(newParent[i]).add(i);
        }

        // Step 3: Calculate subtree sizes in the final tree
        int[] answer = new int[n];
        computeSizes(0, newTree, answer);
        
        return answer;
    }

    private void findNewParents(int node, List<List<Integer>> tree, String s, int[] lastSeen, int[] newParent) {
        int charIdx = s.charAt(node) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (prevAncestor != -1) {
            newParent[node] = prevAncestor;
        }

        lastSeen[charIdx] = node;
        for (int child : tree.get(node)) {
            findNewParents(child, tree, s, lastSeen, newParent);
        }
        // Backtrack so sibling branches don't see this node as an ancestor
        lastSeen[charIdx] = prevAncestor;
    }

    private int computeSizes(int node, List<List<Integer>> newTree, int[] answer) {
        int size = 1;
        for (int child : newTree.get(node)) {
            size += computeSizes(child, newTree, answer);
        }
        answer[node] = size;
        return size;
    }
}
