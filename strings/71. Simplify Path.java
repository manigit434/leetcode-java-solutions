import java.util.*;

public class Solution {
    public String simplifyPath(String path) {
        // Step 1: Split the path by slashes.
        // e.g., "/home//foo/" becomes ["", "home", "", "foo", ""]
        String[] components = path.split("/");
        
        // Use a stack to track our current active directories
        Stack<String> stack = new Stack<>();
        
        // Step 2: Process each component
        for (String directory : components) {
            // If it's a double dot "..", we move up one directory level
            if (directory.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop(); // Remove the last directory we entered
                }
            } 
            // If it's empty or a single dot ".", do nothing (stay in current directory)
            else if (directory.equals(".") || directory.isEmpty()) {
                continue;
            } 
            // Otherwise, it's a valid directory name, so add it to our path
            else {
                stack.push(directory);
            }
        }
        
        // Step 3: Rebuild the simplified canonical path from the stack
        StringBuilder result = new StringBuilder();
        
        // Stacks read from bottom to top when using a traditional for-each loop in Java
        for (String dir : stack) {
            result.append("/").append(dir);
        }
        
        // If the stack was empty, we are at the root directory "/"
        if (result.length() == 0) {
            return "/";
        }
        
        return result.toString();
    }
}
