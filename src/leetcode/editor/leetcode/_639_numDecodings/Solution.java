package leetcode.editor._639_numDecodings;

class Solution {
    static final char star = '*';
    static final char zero = '0';
    static final int pivot = 1000000007;

    public int numDecodings(String s) {
        // 简单的动态规划
        int size = s.length();
        long[] dp = new long[size];
        // base case
        char[] chars = s.toCharArray();
        dp[0] = singleChar(chars[0]);
        // 当成一个
        if (size == 1) {
            return (int) dp[0];
        }
        dp[1] = dp[0] * singleChar(chars[1]) + doubeChar(chars[0], chars[1]);
        // 当成两个
        for (int i = 2; i < size; i++) {
            dp[i] = (dp[i - 1] * singleChar(chars[i]) + dp[i - 2] * doubeChar(chars[i - 1], chars[i])) % pivot;
        }
        return (int) ((dp[size - 1] % pivot));
    }

    private int singleChar(char ch) {
        if (ch == star) {
            // 1~9
            return 9;
        } else if (ch == zero) {
            return 0;
        }
        return 1;
    }

    private int doubeChar(char a, char b) {
        if (a == star) {
            // a只能表示1~2
            // b不能表示0
            // 此时只关心b
            if (b == star) {
                return 15;
            } else if (b < '7') {
                return 2;
            } else {
                return 1;
            }
        } else if (a == zero || a > '2') {
            return 0;
        } else if (a == '2') {
            if (b == star) {
                // 0~6(×)
                // 1~6("*"不能表示0)
                return 6;
            } else if (b < '7') {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (b == star) {
                return 9;
            }
            return 1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.numDecodings("**");
    }
}