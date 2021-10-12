package leetcode.editor.meituan._009_;

import java.util.Scanner;

public class Main {
    // 使用动态规划
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pivot = 998244353;
        // 不超过n元
        int n = scanner.nextInt();
        // 购买的个数
        int m = scanner.nextInt();
        int[][] dp = new int[m][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1;
        }
        for (int j = 0; j < m - 1; j++) {
            for (int i = 1; i <= n; i++) {
                int curr = i;
                int index = 1;
                do {
                    dp[j + 1][curr] += dp[j][i];
                    dp[j + 1][curr] %= pivot;
                    index++;
                    curr = index * i;
                } while (curr <= n);
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += dp[m - 1][i];
            ans %= pivot;
        }
        System.out.print(ans);
    }
}
