package leetcode.editor._1043_maxSumAfterPartitioning;

class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        // 简单的动态规划
        // 长度为500!
        int size = arr.length;
        // 优化方法: 使用同样是动态规划求出区间内最大值,避免核心代码每次都计算相同的部分?
        int[][] dp = new int[size][size];
        for (int i = 0; i < size; i++) {
            dp[i][i] = arr[i];
            dp[i][i] = arr[i];
        }
        // 长度len
        for (int len = 2; len <= k; len++) {
            // 起点i
            for (int i = 0; i + len - 1 < size; i++) {
                int end = i + len - 1;
                for (int j = i; j < i + len - 1; j++) {
                    int maxNum = Math.max(dp[i][j] / (j - i + 1), dp[j + 1][end] / (end - j));
                    dp[i][end] = Math.max(maxNum * len, dp[i][end]);
                }
            }
        }
        for (int i = 2; i <= size; i++) {
            for (int m = 0; m + i - 1 < size; m++) {
                int start = m;
                int end = m + i - 1;
                for (int n = start; n - start + 1 <= k && n < end; n++) {
                    int temp = dp[start][n] + dp[n + 1][end];
                    if (temp > dp[start][end]) {
                        dp[start][end] = temp;
                    }
                }
            }
        }
        return dp[0][size - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.maxSumAfterPartitioning(new int[]{1, 15, 7, 9, 2, 5, 10}, 3);
        System.out.println(ans);
    }
}