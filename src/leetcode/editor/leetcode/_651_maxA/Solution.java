package leetcode.editor._651_maxA;

public class Solution {
    /**
     * @param N: an integer
     * @return: return an integer
     */
    public int maxA(int N) {
        // write your code here
        if (N <= 6) {
            return N;
        }
        int dp[] = new int[N];
        for (int i = 0; i <= 5; i++) {
            dp[i] = i + 1;
        }
        int result;
        for (int i = 6; i < N; i++) {
            // 只能以A结尾或CTL+V结尾
            // 剪切板上的值
            result = i + 1;
            for (int j = 0; j < i - 2; j++) {
                // j为使用CTL+V的次数
                result = Math.max(result, dp[i - 2 - j] + dp[i - 2 - j] * j);
            }
            dp[i] = result;
        }
        return dp[N - 1];
    }
}