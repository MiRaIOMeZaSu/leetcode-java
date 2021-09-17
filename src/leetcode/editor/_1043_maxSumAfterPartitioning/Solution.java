package leetcode.editor._1043_maxSumAfterPartitioning;

class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        // 简单的动态规划
        // 长度为500!
        int size = arr.length;
        int[][][] dp = new int[size][size][k + 1];
        for (int i = 0; i < size; i++) {
            dp[i][i][1] = arr[i];
            dp[i][i][1] = arr[i];
        }
        // 长度len
        for (int len = 2; len <= size; len++) {
            // 起点i
            for (int i = 0; i + len - 1 < size; i++) {
                int end = i + len - 1;
                for (int j = i; j < i + len - 1; j++) {
                    int maxNum = Math.max(dp[i][j][1]/(j - i + 1), dp[j + 1][end][1]/(end - j));
                    dp[i][end][1] = Math.max(maxNum * len, dp[i][end][1]);
                }
            }
        }
        for (int currK = 2; currK <= k; currK++) {
            for (int i = currK; i <= size; i++) {
                for (int m = 0; m + i - 1 < size; m++) {
                    int start = m;
                    int end = m + i - 1;
                    for (int n = start; n < end; n++) {
                        int temp = dp[start][n][1] + dp[n + 1][end][currK - 1];
                        if (temp > dp[start][end][currK]) {
                            if(start==0&&end==size - 1&&currK==k){
                                System.out.println();
                            }
                            dp[start][end][currK] = temp;
                        }
                    }
                }
            }
        }
        return dp[0][size - 1][k];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.maxSumAfterPartitioning(new int[]{1, 15, 7, 9, 2, 5, 10}, 3);
        System.out.println(ans);
    }
}