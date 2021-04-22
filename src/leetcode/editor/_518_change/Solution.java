package leetcode.editor._518_change;

class Solution {
    public int change(int amount, int[] coins) {
        // 01背包问题
        if (amount == 0) {
            return 1;
        }
        int[] dp = new int[amount + 1];
        for (int coin : coins) {
            if (coin <= amount) {
                dp[coin] += 1;
                for (int i = coin; i <= amount; i++) {
                    dp[i] += dp[i - coin];
                }
            }
        }
        return dp[amount];
    }
}