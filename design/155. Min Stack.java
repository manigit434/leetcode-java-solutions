import java.util.Stack;

class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    // Initializes the stack object
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    // Pushes the element val onto the stack
    public void push(int val) {
        stack.push(val);
        // The tracking stack records the current minimum element up to this point
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }
    
    // Removes the element on the top of the stack
    public void pop() {
        // If the top element is the current minimum, remove it from the tracking stack too
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }
    
    // Gets the top element of the stack
    public int top() {
        return stack.peek();
    }
    
    // Retrieves the minimum element in the stack
    public int getMin() {
        return minStack.peek();
    }
}

/*
 * Time Complexity:
 * - push(val): O(1)
 * - pop(): O(1)
 * - top(): O(1)
 * - getMin(): O(1)
 * All operations run in absolute constant time.
 *
 * Space Complexity: O(N)
 * Where N is the total number of elements pushed. In the worst-case scenario (e.g., elements 
 * added in strictly decreasing order), the minStack will store a parallel record for every element.
 */
