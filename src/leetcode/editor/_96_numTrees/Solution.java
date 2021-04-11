package leetcode.editor._96_numTrees;

class Solution {
    int dp[];

    public int numTrees(int n) {
        // 动态规划
        // 直接计算长度即可
        if (n <= 2) {
            return n;
        }
        dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}