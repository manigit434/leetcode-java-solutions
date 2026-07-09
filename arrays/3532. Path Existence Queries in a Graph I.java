class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Array to store the component ID for each node index
        int[] componentId = new int[n];
        int currentId = 0;
        componentId[0] = currentId;
        
        // Group nodes into connected components based on the sorted gaps
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                currentId++;
            }
            componentId[i] = currentId;
        }
        
        // Process each query in O(1) time
        boolean[] answer = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            
            // If they belong to the same component, a path exists
            answer[i] = (componentId[u] == componentId[v]);
        }
        
        return answer;
    }
}
