package com.algorithm.demo.stack;

import java.util.Stack;

public class 小括号匹配 {

    public static void main(String[] args) {
        boolean result = matchParentheses("()");
        System.out.println("result=" + result);
    }

    /**
     * @param string: A string
     * @return: whether the string is a valid parentheses
     */
    public static boolean matchParentheses(String string) {
        int matched = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                matched++;
            }
            if (string.charAt(i) == ')') {
                matched--;
            }
            if (matched < 0) {
                return false;
            }
        }
        return matched == 0;
    }

    public static boolean matchParentheses2(String string) {
        if (string.length() % 2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for (char c : string.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        return stack.isEmpty();
    }


}
