package leetcode.editor._799_champagneTower;

class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        // 使用动态规划存储每个杯子中流出的数量
        if (poured == 0) {
            return 0;
        }
        if (query_row == 0) {
            if (poured > 1) {
                return 1;
            } else {
                return poured;
            }
        }
        double[][] dp = new double[100][100];
        double two = 2d;
        dp[0][0] = poured - 1;
        for (int i = 1; i < 100; i++) {
            int mid = (i + 1) >> 1;
            for (int j = 0; j <= i; j++) {
                if (j > 0) {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) / two;
                } else {
                    dp[i][j] = dp[i - 1][j] / two;
                }
                if (query_row == i && query_glass == j) {
                    return dp[i][j] >= 1 ? 1 : dp[i][j];
                }
                if (dp[i][j] > 1) {
                    dp[i][j] -= 1;
                } else {
                    dp[i][j] = 0;
                }
            }
            if (dp[i][mid] == 0) {
                return 0;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.champagneTower(100000009,
                33,
                17);
    }
}