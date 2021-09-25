package leetcode.editor._583_minDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class Solution {
    static final char a = 'a';

    public int minDistance(String word1, String word2) {
        // 直接寻找最长公共子序列
        int longSize, shortSize;
        char[] longWord, shortWord;
        int len1 = word1.length();
        int len2 = word2.length();
        boolean flag = len1 > len2;
        longSize = flag ? len1 : len2;
        shortSize = flag ? len2 : len1;
        longWord = flag ? word1.toCharArray() : word2.toCharArray();
        shortWord = flag ? word2.toCharArray() : word1.toCharArray();
        Integer[] list = new Integer[26];
        for (int i = 0; i < longSize; i++) {
            int index = longWord[i] - a;
            if (list[index] == null) {
                list[index] = i;
                continue;
            }
            list[index] = Math.min(i, list[index]);
        }
        int ans = 0;
        int[][] dp = new int[shortSize][longSize];
        boolean[] curr = new boolean[26];
        int index = shortWord[0] - a;
        curr[index] = true;
        if (list[index] != null) {
            for (int i = list[index]; i < longSize; i++) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < shortSize; i++) {
            int longIndex = longWord[0] - a;
            int shortIndex = shortWord[i] - a;
            curr[shortIndex] = true;
            if (curr[longIndex]) {
                dp[i][0] = 1;
            }
            for (int j = 1; j < longSize; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                dp[i][j] = Math.max(dp[i][j - 1], dp[i][j]);
                if (longWord[j] == shortWord[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
                }
            }
        }
        return longSize + shortSize - (dp[shortSize - 1][longSize - 1] * 2);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minDistance("sea",
                "eat"));
    }
}