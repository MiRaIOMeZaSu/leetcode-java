package leetcode.editor._516_longestPalindromeSubseq;


class Solution {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        char c[] = s.toCharArray();
        int dp[][] = new int[len][len];
        dp[0][0] = 1;
        // 初始化i==j时的值
        for (int i = len - 1; i > 0; i--) {
            dp[i][i] = 1;
            dp[i][i - 1] = 0;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j <= len - 1; j++) {
                if (c[i] == c[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = dp[i + 1][j] > dp[i][j - 1] ? dp[i + 1][j] : dp[i][j - 1];
                }
            }
        }
        return dp[0][len - 1];
    }
}