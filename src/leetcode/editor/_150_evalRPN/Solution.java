package leetcode.editor._150_evalRPN;

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int evalRPN(String[] tokens) {
        // 使用函数栈
        Deque<String> strings = new LinkedList<>();
        int length = tokens.length;
        for (int i = 0; i < length; i++) {
            if (Character.isDigit(tokens[i].charAt(tokens[i].length() - 1))) {
                strings.push(tokens[i]);
            } else {
                int b = Integer.parseInt(strings.pop());
                int a = Integer.parseInt(strings.pop());
                strings.push(String.valueOf(applyArithmetic(a, b, tokens[i])));
            }
        }
        return Integer.parseInt(strings.pop());
    }


    private int applyArithmetic(int a, int b, String method) {
        if ("+".equals(method)) {
            return a + b;
        } else if ("-".equals(method)) {
            return a - b;
        } else if ("*".equals(method)) {
            return a * b;
        } else {
            return a / b;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.evalRPN(new String[]{
                "4", "-2", "/", "2", "-3", "-", "-"
        }));
    }
}