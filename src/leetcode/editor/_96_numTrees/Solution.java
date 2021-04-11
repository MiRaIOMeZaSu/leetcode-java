package leetcode.editor._96_numTrees;

class Solution {
    int dp[][];

    public int numTrees(int n) {
        // 动态规划
        // dp[i][j] 表示i到j范围内所有可能的二叉搜索树的数量
        this.dp = new int[n + 1][n + 1];
        dp[1][1] = 1;
        // 空的一样是零
        dp[1][0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i + 1][i + 1] = 1;
            dp[i][i + 1] = 2;
            dp[i + 1][i] = 1;
        }
        for (int i = 3; i <= n; i++) {
            // 每次的区间长度为i
            int index = 1;
            while (index + i - 1 <= n) {
                dp[index][index + i - 1] = compute(index, index + i - 1);
                index++;
            }
        }
        return dp[1][n];
    }

    public int compute(int left, int right) {
        int ret = 0;
        for (int i = left; i <= right; i++) {
            // 以i为根结点
            ret += dp[left][i - 1] * dp[i + 1][right];
        }
        return ret;
    }
}