package leetcode.editor.binarysearch._724;

class Solution {
    public int solve(int n, int k) {
        // 在n次游戏中至多赢k次的所有可能性
        // 动态规划?
        // 只需要计算一半的数量
//        n ≤ 10,000,因此不能排列或暴力获取
//        k ≤ 10
        // 加法相同,base case变化
        int ret = 0;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= k; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
            dp[i][1] = i;
            dp[i][i - 1] = i;
        }
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
            dp[i][0] = 1;
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 2; j <= n; j++) {
                dp[j][i] = dp[j - 1][i] + dp[j - 1][i - 1];
            }
        }


        return ret;
    }
}