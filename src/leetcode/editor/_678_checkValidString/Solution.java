package leetcode.editor._678_checkValidString;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Solution {
    char leftBracket = '(';
    char rightBracket = ')';
    char blank = '*';

    public boolean checkValidString(String s) {
        // 计算左右括号的数量差
        // 使用回溯法,选择当做空的或选择当做括号
        int[] count = new int[2];
        int size = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < size; i++) {
            if (chars[i] == leftBracket) {
                count[0]++;
            } else if (chars[i] == blank) {
            } else if (chars[i] == rightBracket) {
                count[1]++;
            }
        }
        return solve(chars, new LinkedList<>(), 0);
    }

    private boolean solve(char[] chars, Deque<Character> stack, int index) {
        if (index >= chars.length && stack.isEmpty()) {
            return true;
        }
        for (int i = index; i < chars.length; i++) {
            if (chars[i] == leftBracket) {
                stack.push(chars[i]);
            } else if (chars[i] == rightBracket) {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (chars[i] == blank) {
                if (solve(chars, new LinkedList<>(stack), i + 1)) {
                    return true;
                }
                Deque<Character> temp = new LinkedList<>(stack);
                temp.push(leftBracket);
                if (solve(chars, new LinkedList<>(temp), i + 1)) {
                    return true;
                }
                if (!stack.isEmpty()) {
                    temp = new LinkedList<>(stack);
                    temp.pop();
                    return solve(chars, new LinkedList<>(temp), i + 1);
                }
                return false;
            }
        }
        // 此时遍历到末尾
        for (char ch : stack
        ) {
            if (ch != blank) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.checkValidString("*)"));
    }
}