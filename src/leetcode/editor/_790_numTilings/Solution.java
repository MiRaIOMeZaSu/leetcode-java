package leetcode.editor._790_numTilings;

class Solution {
    public int numTilings(int N) {
        int[][] dp = new int[N + 1][4];
        int pivot = 1000000007;
        dp[1][0] = 1;
        // 0: 满
        if (N == 1) {
            return dp[1][0];
        }
        dp[2][1] = 1;
        // 1: 缺上
        dp[2][2] = 1;
        // 2: 缺下
        dp[2][3] = 1;
        // 3: 缺双上
        for (int i = 2; i <= N; i++) {
            dp[i][3] += dp[i - 2][0] % pivot;
            int[] nums = new int[]{
                    dp[i - 1][0], dp[i][3], dp[i - 1][1], dp[i - 1][2]
            };
            for (int j = 0; j < 4; j++) {
                dp[i][0] += nums[j];
                dp[i][0] %= pivot;
            }
            dp[i][1] += (dp[i - 1][2] + dp[i - 2][0]) % pivot;
            dp[i][2] += (dp[i - 1][1] + dp[i - 2][0]) % pivot;
        }
        return dp[N][0] % pivot;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.numTilings(1000);
    }
}