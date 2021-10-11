package leetcode.editor.meituan._003_;

/*
输入：
- 输入第一行包含两个正整数 n，m，表示订单的数量和小美可以接的订单数量。
- 接下来有 n 行，第 i 行表示 i-1 号订单的信息。每行有两个正整数 v 和 w  ，表示一个订单的跑腿价格和商品重量。
输出：
- 输出包含 m 个 1~n 之间的正整数，中间用空格隔开，表示选择的订单编号。
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // 简单的动态规划
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        int[][] orders = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            temp = bufferedReader.readLine().split(" ");
            int base = Integer.parseInt(temp[0]);
            int weigth = Integer.parseInt(temp[1]);
            orders[i][0] = i;
            orders[i][1] = base + weigth * 2;
        }
        Arrays.sort(orders, ((o1, o2) -> o2[1] - o1[1]));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            stringBuilder.append(orders[i][0]);
            stringBuilder.append(" ");
        }
        System.out.println(stringBuilder.toString().trim());
    }
}
