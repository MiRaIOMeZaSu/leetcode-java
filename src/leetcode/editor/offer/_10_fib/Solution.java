package leetcode.editor.offer._10_fib;

class Solution {
    private final int pivot = 1000000007;

    public int fib(int n) {
        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = 1;
        if (n < 2) {
            return dp[n];
        }
        int curr = 2;
        for (int i = 2; i <= n; i++) {
            dp[curr] = (dp[(curr + 1) % 3] + dp[(curr + 2) % 3]) % pivot;
            curr++;
            curr %= 3;
        }
        return dp[(curr + 2) % 3];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.fib(2);
    }
}