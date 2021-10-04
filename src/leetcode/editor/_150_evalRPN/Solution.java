package leetcode.editor._150_evalRPN;

class Solution {
    public int evalRPN(String[] tokens) {
        // 使用函数栈
        return privateEvalRPN(tokens, 0, tokens.length - 1);
    }

    private int privateEvalRPN(String[] tokens, int start, int end) {
        int length = end - start + 1;
        if (length == 1) {
            return Integer.parseInt(tokens[start]);
        }
        if (Character.isDigit(tokens[end - 1].charAt(tokens[end - 1].length() - 1))) {
            return applyArithmetic(
                    privateEvalRPN(tokens, start, end - 2),
                    Integer.parseInt(tokens[end - 1]),
                    tokens[end]);
        }
        return applyArithmetic(
                Integer.parseInt(tokens[start]),
                privateEvalRPN(tokens, start + 1, end - 1),
                tokens[end]);
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
                "4","-2","/","2","-3","-","-"
        }));
    }
}