package leetcode.editor.acmcoder.meituan2021BE._3;
/*
小美要给n个朋友送礼物，第i个朋友需要送ai个礼物。商店里有m种礼物，第j种礼物的价格为bj。不同的人对的礼物有不同的要求。有的人希望礼物更加贵重，而有的人认为心意到了就行。因此第i位朋友的每种礼物价格均不能低于ci 。

请问给第i位朋友的礼物的最低价格总和为多少？（同一种礼物不能送给同一个人两份，但不同的朋友可以收到相同的礼物）。



输入描述
第一行两个正整数n,m(1≤n,m≤5x104)。

第二行n个正整数a1,a2,......,an(1≤ai≤m)。

第三行m个正整数b1,b2,.....,bn(1≤bi≤106)。

第四行n个正整数c1,c2,....,cn(1≤ci≤106)。

数字间两两有空格隔开

输出描述
仅一行，由一个空格分隔的n个数。第i个数表示第i位朋友收到的礼物的最低价格总和。如果没有合法方案，输出-1。


样例输入
3 5
2 3 3
100 200 300 400 500
200 300 400
样例输出
500 1200 -1

* */


import javax.swing.tree.TreeCellRenderer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        // 背包法
        // 将多大的数字单独存储,并记录较大数的最小值
        // 送礼物的数量是限定的!
        // 要求的是每种礼物的数量!
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] goods = new int[m];
        int[][] friends = new int[n][3];
        int maxNeedCount = 0;
        for (int i = 0; i < n; i++) {
            friends[i][2] = scanner.nextInt();
            maxNeedCount = Math.max(maxNeedCount, friends[i][2]);
        }
        for (int i = 0; i < m; i++) {
            goods[i] = scanner.nextInt();
        }
        int maxNeed = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            friends[i][0] = scanner.nextInt();
            maxNeed = Math.max(maxNeed, friends[i][0]);
        }

        boolean[][] dp = new boolean[maxNeed + 1][];
        dp[0] = new boolean[maxNeedCount + 1];
        dp[0][0] = true;
        int currSum = 0;
        long[] minBorder = new long[maxNeedCount + 1];
        for (int i = 0; i < m; i++) {
            // 开始填充背包
            for (int j = Math.min(currSum,maxNeed); j >= 0; j--) {
                if (dp[j]!=null) {
                    for (int k = 0; k < maxNeedCount; k++) {
                        int target = j + goods[i];
                        if (dp[j][k]) {
                            if (j > maxNeed - goods[i]) {
                                long temp = 0;
                                temp += j;
                                temp += goods[i];
                                minBorder[k + 1] = Math.min(temp, minBorder[k + 1]);
                            } else {
                                if (dp[target] == null) {
                                    dp[target] = new boolean[maxNeedCount + 1];
                                }
                                dp[target][k + 1] = true;
                            }
                        }
                    }

                }
            }
            currSum += goods[i];
        }
        // 使用friend[i][0]存储需求
//        int[][] nFriends = new int[n][];
//        for (int i = 0; i < n; i++) {
//            nFriends[i] = friends[i];
//        }
//        Arrays.sort(nFriends, Comparator.comparingInt(o -> o[2]));
        int pointer = 0;
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < maxNeed; i++) {
            if (dp[i] != null) {
                treeSet.add(i);
            }
        }
        for (int i = 0; i < n; i++) {
            int start = treeSet.ceiling(friends[i][0]);
            long localAns = -1;
            for (int j = start; j < maxNeed; j++) {
                if (dp[j] != null) {
                    if (dp[j][friends[i][2]]) {
                        localAns = j;
                    }
                }
            }
            // 打印
            if (localAns == -1) {
                if (minBorder[friends[i][2]] != 0) {
                    localAns = minBorder[friends[i][2]];
                }
            }
            System.out.print(localAns + " ");
        }
    }
}
