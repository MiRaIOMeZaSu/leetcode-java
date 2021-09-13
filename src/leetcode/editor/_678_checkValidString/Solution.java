package leetcode.editor._678_checkValidString;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Solution {
    char leftBracket = '(';
    char rightBracket = ')';
    char blank = '*';
    int pivot;
    int blankCount = 0;

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
                blankCount++;
            } else {
                count[1]++;
            }
        }
        pivot = Math.abs(count[0] - count[1]);
//        if (pivot > blankCount) {
//            return false;
//        }

        if (solve(chars, new LinkedList<>(), 0, 0)) {
            return true;
        }
        char[] chars1 = new char[size];
        HashMap<Character,Character> hashMap = new HashMap<>();
        hashMap.put('(',')');
        hashMap.put(')','(');
        hashMap.put('*','*');
        for (int i = 0; i < size; i++) {
            chars1[i] = hashMap.get(chars[size - 1 - i]);
        }
        return solve(chars1, new LinkedList<>(), 0, 0);
    }

    private boolean solve(char[] chars, Deque<Character> stack, int index, int blankUsedCount) {
        if (index >= chars.length && stack.isEmpty()) {
            return true;
        }
//        if (blankUsedCount > pivot) {
//            return false;
//        }
        for (int i = index; i < chars.length; i++) {
            if (chars[i] != rightBracket) {
                stack.push(chars[i]);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.peek() == leftBracket) {
                    stack.pop();
                } else {
                    // 下一轮递归
                    stack.pop();
                    boolean a = solve(chars, new LinkedList<>(stack), i + 1, blankUsedCount + 1);
                    if (a) {
                        return true;
                    } else {
                        return solve(chars, stack, i, blankUsedCount);
                    }
                }

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
        System.out.println(solution.checkValidString("**))*(*"));
//        System.out.println(solution.checkValidString("(*)"));
//        System.out.println(solution.checkValidString("(*)))"));
    }
}