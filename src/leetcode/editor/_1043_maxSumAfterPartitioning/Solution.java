package leetcode.editor._1043_maxSumAfterPartitioning;

class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        // 简单的动态规划
        // 长度为500!
        int size = arr.length;
        int[][][][] dp = new int[size][size][k + 1][2];
        for (int i = 0; i < size; i++) {
            dp[i][i][1][0] = arr[i];
            dp[i][i][1][1] = arr[i];
        }
        // 长度len
        for (int len = 2; len < size; len++) {
            // 起点i
            for (int i = 0; i + len - 1 < size; i++) {
                int end = i + len - 1;
                for (int j = i; j < i + len - 1; j++) {
                    int maxNum = Math.max(dp[i][j][1][0], dp[j + 1][end][1][0]);
                    dp[i][end][1][0] = Math.max(maxNum, dp[i][end][1][0]);
                }
                dp[i][end][1][1] = dp[i][end][1][0] * len;
            }
        }
        for (int currK = 2; currK <= k; currK++) {
            for (int i = 2; i <= currK; i++) {
                for (int m = 0; m + i - 1 < size; m++) {
                    int start = m;
                    int end = m + i - 1;
                    for (int n = start; n < end; n++) {
                        int maxNum = Math.max(dp[start][n][1][0], dp[n + 1][end][currK - 1][0]);
                        if(maxNum > dp[start][end][currK][0]){
                            dp[start][end][currK][0] = maxNum;
                            dp[start][end][currK][1] = maxNum * i;
                        }
                    }
                }
            }
        }

        return dp[0][size - 1][k][1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.maxSumAfterPartitioning(new int[]{1,15,7,9,2,5,10},3);
    }
}