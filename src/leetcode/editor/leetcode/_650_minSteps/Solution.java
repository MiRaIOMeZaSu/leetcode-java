package leetcode.editor._650_minSteps;

class Solution {
    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        // 使用动态规划
        // 只有复制和粘贴两种操作!
        int[][] dp = new int[n + 1][2];
        dp[1][0] = 1;
        dp[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i][1] = i;
        }
        for (int i = 2; i <= n; i++) {
            // 当前数量为i
            dp[i][0] = dp[i][1] + 1;
            int times = 2;
            int product = times * i;
            while (product <= n) {
                dp[product][1] = Math.min(dp[product][1], dp[i][0] + times - 1);
                times++;
                product = times * i;
            }
        }
        return dp[n][1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minSteps(4);
    }
}