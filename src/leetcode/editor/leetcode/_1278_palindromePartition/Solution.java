package leetcode.editor._1278_palindromePartition;

class Solution {
    public int palindromePartition(String s, int k) {
        int size = s.length();
        char[] chars = s.toCharArray();
        if (k == 1) {
            return toChange(chars, 0, size - 1);
        }
        int[][][] dp = new int[size][size][k + 1];
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                dp[i][j][1] = toChange(chars, i, j);
            }
        }
        for (int i = 1; i < size; i++) {
            // 长度
            for (int j = 0; j + i - 1 < size; j++) {
                // 起点
                for (int l = 2; l <= Math.min(k - 1, i); l++) {
                    // 总k的数量
                    dp[j][j + i - 1][l] = Integer.MAX_VALUE;
                    for (int m = 1; i - m >= l - 1; m++) {
                        // 第一段的长度
                        dp[j][j + i - 1][l] = Math.min(dp[j][j + i - 1][l],
                                dp[j][j + m - 1][1] + dp[j + m][j + i - 1][l - 1]
                        );
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; size - i >= k - 1; i++) {
            ans = Math.min(ans, dp[0][i - 1][1] + dp[i][size - 1][k - 1]);
        }
        return ans;
    }

    private int toChange(char[] chars, int i, int j) {
        int ans = 0;
        while (i < j) {
            if (chars[i] != chars[j]) {
                ans++;
            }
            i++;
            j--;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.palindromePartition("abcdefg", 2));
    }
}