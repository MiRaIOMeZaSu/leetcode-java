package leetcode.editor._405_toHex;

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        // 除法
        // 输入的是int
        char[] table = new char[16];
        for (int i = 0; i <= 9; i++) {
            table[i] = String.valueOf(i).charAt(0);
        }
        for (int i = 0; i < 6; i++) {
            table[i + 10] = (char) ('a' + i);
        }
        int curr = 1;
        int count = 0;
        int localCurr = 1;
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < 32; i++) {
            if (i % 4 == 0 && i != 0) {
                stack.push(table[count]);
                localCurr = 1;
                count = 0;
            }
            if ((num | curr) == num) {
                count |= localCurr;
            }
            curr = curr << 1;
            localCurr = localCurr << 1;
        }
        stack.push(table[count]);
        while (!stack.isEmpty() && stack.peek() == '0') {
            stack.pop();
        }
        String ans = "";
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.toHex(10));
    }
}