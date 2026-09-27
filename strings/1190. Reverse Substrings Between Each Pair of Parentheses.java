import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(sb.length());
            } else if (c == ')') {
                int start = stack.pop();
                String reversed = new StringBuilder(sb.substring(start)).reverse().toString();
                sb.replace(start, sb.length(), reversed);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
