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
        // 使用动态规划,按长度递增
        int size = s.length();
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            if (chars[i] == blank) {
                dp[i][i] = true;
            }
        }
        for (int i = 0; i < size - 1; i++) {
            if ((dp[i][i] || chars[i] == leftBracket) && (dp[i + 1][i + 1] || chars[i + 1] == rightBracket)) {
                dp[i][i + 1] = true;
            }
        }
        for (int i = 3; i <= size; i++) {
            for (int j = 0; j + i - 1 < size; j++) {
                int start = j;
                int end = i + j - 1;
                if ((dp[start][start] || chars[start] == leftBracket) && (dp[end][end] || chars[end] == rightBracket) && dp[start + 1][end - 1]) {
                    dp[start][end] = true;
                    continue;
                }
                for (int p = start; p < end; p++) {
                    if (dp[start][p] && dp[p + 1][end]) {
                        dp[start][end] = true;
                        break;
                    }
                }
            }
        }
        return dp[0][size - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.checkValidString("((**)");
    }
}