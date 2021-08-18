package leetcode.editor._552_checkRecord;

class Solution {
    private int pivot = 1000000007;

    public int checkRecord(int n) {
        // 动态规划
        // 已经连续的次数
        int[][][] dp = new int[n][3][2];
        dp[0][1][0] = 1;
        dp[0][0][1] = 1;
        dp[0][0][0] = 1;
        for (int i = 1; i < n; i++) {
            // 此处选择P
            for (int p = 0; p < 2; p++) {
                for (int j = 0; j < 3; j++) {
                    dp[i][0][p] += dp[i - 1][j][p];
                    dp[i][0][p] = check(dp[i][0][p]);
                }
            }
            // 此处选择A
            for (int j = 0; j < 3; j++) {
                dp[i][0][1] += dp[i - 1][j][0];
                dp[i][0][1] = check(dp[i][0][1]);
            }
            // 此处选择L
            for (int p = 0; p < 2; p++) {
                for (int j = 1; j < 3; j++) {
                    dp[i][j][p] = dp[i - 1][j - 1][p];
                    dp[i][j][p] = check(dp[i][j][p]);
                }
            }
        }
        int result = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                result += dp[n - 1][i][j];
                result = check(result);
            }
        }
        return result;
    }

    private int check(int i) {
        if (i >= pivot) {
            i = i % pivot;
        }
        return i;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int reuslt = solution.checkRecord(1);
        System.out.println(reuslt);
    }
}