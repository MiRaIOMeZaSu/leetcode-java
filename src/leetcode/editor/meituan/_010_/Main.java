
package leetcode.editor.meituan._010_;

/*
小团从某不知名论坛上突然得到了一个测试默契度的游戏，想和小美玩一次来检验两人的默契程度。游戏规则十分简单，首先给出一个长度为 n 的序列，最大值不超过 m 。
小团和小美各自选择一个 [1,m] 之间的整数，设小美选择的是 l ，小团选择的是 r ，我们认为两个人是默契的需要满足以下条件:

l 小于等于 r 。
对于序列中的元素 x ，如果 0<x<l ，或 r<x<m+1 ,则 x 按其顺序保留下来，要求保留下来的子序列单调不下降。
小团为了表现出与小美最大的默契，因此事先做了功课，他想知道能够使得两人默契的二元组 <l,r> 一共有多少种。
我们称一个序列 A 为单调不下降的，当且仅当对于任意的 i>j ，满足 A[i]>=A[j] 。

格式：


输入：
- 输入第一行包含两个正整数 m 和 n ，表示序列元素的最大值和序列的长度。
- 输入第二行包含 n 个正整数，表示该序列。
输出：
- 输出仅包含一个整数，表示能使得两人默契的二元组数量。
示例：


输入：
     5 5
     4 1 4 1 2
输出：10
提示：

输入：
     2 2
     1 2
输出：3
提示：

1 <= n, m <= 100000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/yqj8Su
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 单调下降
        // 移除的数字为[l,r]
        // 选择移除更多小数或移除更多大数
        // 要使后面的比前面的大(不绝对单调递增)
        int m, n;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bufferedReader.readLine().trim().split(" ");
        m = Integer.parseInt(temp[0]);
        n = Integer.parseInt(temp[1]);
        temp = bufferedReader.readLine().trim().split(" ");
        int[] arr = new int[n];
        List<Integer> sortedArr = new ArrayList<>();
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(temp[i]);
            if (map.containsKey(arr[i])) {
                map.get(arr[i])[1] = i;
            } else {
                sortedArr.add(arr[i]);
                map.put(arr[i], new int[]{i, i});
            }
        }
        //        int lastMax = Integer.MIN_VALUE;
        //        boolean flag = true;
        //        for (int i = 0; i < n; i++) {
        //
        //        }
        //        if (flag) {
        //            // 输入序列是满足完全顺序的
        //        }
        sortedArr.sort(Comparator.naturalOrder());
        // TODO:应该从两边往中间找可取的范围,对每个左边都有指定数量的右边

        int distinctNumCount = sortedArr.size();
        // 初始应该添加可以移除所有的情况
        // r的范围[1,sortedArr.get(0)]
        // l的范围[sortedArr.get(distinctNumCount - 1),m]
        int ans = (sortedArr.get(0) - 1 + 1) * (m - sortedArr.get(distinctNumCount - 1) + 1);
        // 对于输入序列的值的范围,应该使剩余值的大小位于范围的一边,才能保证[l,r]移除区间能够覆盖所有应该移除的数
        // 满足正向
        int left = map.get(sortedArr.get(0))[1];
        // r的范围为[sortedArr.get(0) + 1,sortedArr.get(0 + 1)] // 必须把大于sortedArr.get(0)的全部移除
        // l的范围为[sortedArr.get(n - 1),m]
        // 添加只保留第一个的情况
        ans += (sortedArr.get(0 + 1) - (sortedArr.get(0) + 1) + 1) * (m - sortedArr.get(distinctNumCount - 1) + 1);
        int i = 1;
        for (; i < distinctNumCount - 1; i++) {
            // 添加保留第一个到第i+1个的情况
            int[] border = map.get(sortedArr.get(i));
            if (border[0] > left) {
                ans += (sortedArr.get(i + 1) - (sortedArr.get(i) + 1) + 1) * (m - sortedArr.get(distinctNumCount - 1) + 1);
                left = border[1];
            } else {
                break;
            }
        }
        // 判断最后一个的情况
        if (i == distinctNumCount - 1 && map.get(sortedArr.get(distinctNumCount - 1))[0] > left) {
            ans += (m - (sortedArr.get(distinctNumCount - 1) + 1) + 1) * (m - (sortedArr.get(distinctNumCount - 1) + 1) + 1);
        }
        // 添加只保留最后一个的情况
        int right = map.get(sortedArr.get(distinctNumCount - 1))[0];
        ans += (sortedArr.get(0) - 1 + 1)
                * (
                (sortedArr.get(distinctNumCount - 1) - 1)
                        - sortedArr.get(distinctNumCount - 2)
                        + 1
        );
        i = distinctNumCount - 2;
        for (; i > 0; i--) {
            // 添加移除第一个到第i-1个的情况
            int[] border = map.get(sortedArr.get(i));
            if (border[1] < right) {
                ans += (sortedArr.get(0) - 1 + 1) *
                        (
                                (sortedArr.get(i) - 1)
                                        - sortedArr.get(i - 1)
                                        + 1
                        );
                right = border[0];
            }else {
                break;
            }
        }
        if (i == 0 && map.get(sortedArr.get(0))[1] < right) {
            ans += ((sortedArr.get(0) - 1) - 1 + 1) * ((sortedArr.get(0) - 1) - 1 + 1);
        }
        System.out.print(ans);
    }
}
