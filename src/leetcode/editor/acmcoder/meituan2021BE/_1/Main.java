package leetcode.editor.acmcoder.meituan2021BE._1;

/*小美开始学习健身操。我们可以将一套健身操简化为两个动作（记为动作0，动作1）和中途休息（即不做任何动作）的组合。

如果小美在健身操要求做某个动作时做出错误的动作，或者说在应该中途休息是做了某个动作，则被称为“错误操作”（应该做某个动作而没有做某个动作不算“错误操作”）。如果一段连续的错误操作的持续时间小于阈值k，则被称为“小错误”。

例如，要求的动作为：1~3时刻动作0，小美的实际动作为：0~4时刻动作0，阈值为2，则小美有两次“小错误”，分别在0~1时刻和3~4时刻。

再例如，要求的动作为：1~3时刻动作0，小美的实际动作为：2~4时刻动作1，阈值为2。虽然2~3和3~4都是“错误操作”，但是它们是连续的，所以只算一段错误操作，时间等于2，达到阈值，因此小美没有“小错误”。

给出健身操要求的动作以及小美的实际动作，求小美有多少次“小错误”。



输入描述
第一行三个正整数n,m,k(1≤n,m≤200,1≤k≤106)，分别表示健身操的动作数量，小美的实际动作数量，阈值。

接下来n行，每行三个正整数l,r,v，表示健身操的动作为，l~r时刻，动作v。保证按时间升序给出指令，各个动作的时间段没有重叠的部分。

接下来m行，每行三个正整数l,r,v，表示小美的实际动作为，l~r时刻，动作v。保证按时间升序给出指令，各个动作的时间段没有重叠的部分。

0≤l≤r≤106

输出描述
仅一行，一个整数，小美的“小错误”的次数。


样例输入
3 3 2
1 2 0
5 6 1
8 10 1
0 3 0
5 6 0
7 9 1
样例输出
4

提示
小错误分别在0~1 , 2~3 , 5~6 , 7~8 ； 根据题意，应该做某个动作而没有做某个动作不算“错误操作”故9~10不在错误的定义内
* */


import java.util.Scanner;

public class Main {
    static int[] shouldDo = new int[1000000];
    static int[] actualDo = new int[1000000];

    public static void main(String[] args) {
        // 使用应该做的对小美做的进行判断
        // 判断
        int n, m, k;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();
        int border = 0;
        border = getBorder(n, scanner, border, shouldDo);
        border = getBorder(m, scanner, border, actualDo);
        // 开始比较
        int ans = 0;
        boolean lastRight = judge(0);
        int left = 0;
        for (int i = 1; i <= border; i++) {
            boolean nowRight = judge(i);
            if (nowRight && nowRight != lastRight) {
                ans += i - left + 1 >= k ? 1 : 0;
            }
            if (!nowRight && lastRight) {
                left = i;
            }
            lastRight = nowRight;
        }
        if (!lastRight) {
            ans += border + 1 - left + 1 >= k ? 1 : 0;
        }
        System.out.println(ans);
    }

    private static int getBorder(int m, Scanner scanner, int border, int[] actualDo) {
        for (int i = 0; i < m; i++) {
            int[] temp = new int[3];
            for (int j = 0; j < 3; j++) {
                temp[j] = scanner.nextInt();
            }
            for (int j = temp[0]; j <= temp[1]; j++) {
                actualDo[j] = temp[2] + 1;
            }
            border = Math.max(border, temp[1]);
        }
        return border;
    }

    private static boolean judge(int index) {
        return actualDo[index] == 0 || actualDo[index] == shouldDo[index];
    }
}
