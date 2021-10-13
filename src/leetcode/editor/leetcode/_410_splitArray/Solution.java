package leetcode.editor._410_splitArray;

class Solution {
    public int splitArray(int[] nums, int m) {
        int size = nums.length;
        // 有分割数量限制(不能小也不能大
        // 从后往前走
        int[][] dp = new int[size][m + 1];
        dp[0][1] = nums[0];
        for (int i = 1; i < size; i++) {
            dp[i][1] = dp[i - 1][1] + nums[i];
        }

        for (int i = 1; i < size; i++) {
            for (int j = 2; j <= m; j++) {
                // 分段数量
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 1; i + 1 - k >= j - 1; k++) {
                    // 最后一段的长度
                    dp[i][j] = Math.min(Math.max(dp[i - k][j - 1], dp[i][1] - dp[i - k][1]), dp[i][j]);
                }
            }
        }
        return dp[size - 1][m];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.splitArray(new int[]{1, 2, 3, 4, 5}, 2);
    }
}