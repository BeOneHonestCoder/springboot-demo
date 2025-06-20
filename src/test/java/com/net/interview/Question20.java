package com.net.interview;

import java.util.Stack;

public class Question20 {

    public static void main(String[] args) {
        boolean result = isValid("()[]{}");
        System.err.println(result);
    }


    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != ch) {
                return false;
            }
        }
        return stack.empty();
    }
}
