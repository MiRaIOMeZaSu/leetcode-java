package leetcode.editor.meituan._011_;

/*
服装店新进了 a 条领带，b 条裤子，c 个帽子，d 件衬衫，现在要把这些搭配起来售卖。
有三种搭配方式，一条领带和一件衬衫，一条裤子和一件衬衫，一个帽子和一件衬衫。
卖出一套领带加衬衫可以得到 e 元，卖出一套裤子加衬衫可以得到 f 元，卖出一套帽子加衬衫可以得到 g 元。现在你需要输出最大获利。

格式：


输入：
- 一行 7 个整数分别表示 a, b, c, d, e, f, g 。
输出：
- 最大获利。
示例：


输入：2 3 4 5 6 7 8
输出：39
解释：4 个帽子加 4 件衬衫获利 32 元，1 条裤子加 1 件衬衫获利 7 元，一共得到 39 元。
* */


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int d;
        int[][] count = new int[3][2];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            count[i][0] = scanner.nextInt();
        }
        d = scanner.nextInt();
        for (int i = 0; i < 3; i++) {
            count[i][1] = scanner.nextInt();
        }
        int ans = 0;
        int times = Math.min(d, count[0][0] + count[1][0] + count[2][0]);
        Arrays.sort(count, ((o1, o2) -> o2[1] - o1[1]));
        int index = 0;
        while (times > 0) {
            int currUse = Math.min(times, count[index][0]);
            ans += currUse * count[index][1];
            times -= currUse;
            index++;
        }
        System.out.print(ans);
    }
}
