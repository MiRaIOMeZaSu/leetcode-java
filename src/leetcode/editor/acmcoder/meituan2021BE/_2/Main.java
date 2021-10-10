package leetcode.editor.acmcoder.meituan2021BE._2;

/*
在一场持续300分钟的算法竞赛中，小美在75分钟后便没有了提交。而在不久之后的另一场比赛中，小美225分钟后便没有了提交。于是被小团调侃用一场比赛的时间打了两场比赛。

       小团打了n场比赛，每场比赛持续时间为m分钟，第i场比赛中，小团ai分钟后便没有了提交。请统计，有多少无序对(i,j)满足ai+aj≤m，以方便小美来调侃小团。



输入描述
第一行两个正整数n,m(1≤n≤5x104，1≤m≤109)。

第二行n个正整数a1,a2,......,an（1≤ai≤109）。

输出描述
仅一行，一个整数表示答案。


样例输入
3 4
1 2 3
样例输出
2
* */


import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 使用线段树计算范围数量
        // 要找的是小于某个值的数量
        // 随着当前数量的增大
        int n, m;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] contest = new int[n];
        HashMap<Integer, Integer> integerIntegerHashMap = new HashMap<>();
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            contest[i] = scanner.nextInt();
            int temp = integerIntegerHashMap.merge(contest[i], 1, Integer::sum);
            if (temp == 1) {
                list1.add(contest[i]);
            }
        }
        // 使用treeMap
        int sum = n;
        int ans = 0;
        list1.sort(Comparator.naturalOrder());
        int right = list1.size() - 1;
        for (int i = 0; i < list1.size() && i <= right; i++) {
            int curr = list1.get(i);
            sum -= integerIntegerHashMap.get(curr);
            while (right > i && list1.get(right) + curr > m) {
                sum -= integerIntegerHashMap.get(list1.get(right));
                right--;
            }
            if (sum > 0) {
                ans += sum;
            }
            if (curr * 2 <= m) {
                // 选择任意两个
                ans += choiceTwo(integerIntegerHashMap.get(curr));
            } else if (sum <= 0) {
                break;
            }
        }
        System.out.println(ans);
    }

    private static int choiceTwo(int count) {
        if (count < 2) {
            return 0;
        }
        return count * (count - 1) / 2;
    }
}
