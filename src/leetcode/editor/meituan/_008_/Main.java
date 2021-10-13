package leetcode.editor.meituan._008_;

/*
小团惹小美生气了，小美要去找小团“讲道理”。小团望风而逃，他们住的地方可以抽象成一棵有n个结点的树，小美位于 x 位置，小团位于 y 位置。
小团和小美每个单位时间内都可以选择不动或者向相邻的位置转移，很显然最终小团会无路可逃，只能延缓一下被“讲道理”的时间，请问最多经过多少个单位时间后，小团会被追上。

格式：


输入：
- 输入第一行包含三个整数 n，x，y，分别表示树上的结点数量，小美所在的位置和小团所在的位置。
- 接下来有 n-1 行，每行两个整数 u，v，表示 u 号位置和 v 号位置之间有一条边，即 u 号位置和 v 号位置彼此相邻。
输出：
- 输出仅包含一个整数，表示小美追上小团所需的时间。
示例：


输入：
     5 1 2
     2 1
     3 1
     4 2
     5 3
输出：2
提示：

1 <= n <= 50000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/vSYUMc
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    private static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        // 寻找所有点中离小美当前点位最远的点?
        // 一共n个点,n-1条边?
        // 环?重复边?无向图?
        // 使用邻接表
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bufferedReader.readLine().trim().split(" ");
        int n, x, y;
        n = Integer.parseInt(temp[0]);
        x = Integer.parseInt(temp[1]);
        y = Integer.parseInt(temp[2]);
        graph = new ArrayList[n + 1];
        for (int i = 0; i < n - 1; i++) {
            temp = bufferedReader.readLine().trim().split(" ");
            int[] tempPoint = new int[2];
            tempPoint[0] = Integer.parseInt(temp[0]);
            tempPoint[1] = Integer.parseInt(temp[1]);
            for (int j = 0; j < 2; j++) {
                if (graph[tempPoint[j]] == null) {
                    graph[tempPoint[j]] = new ArrayList<>();
                }
                graph[tempPoint[j]].add(tempPoint[(j + 1) % 2]);
            }
        }
        int[][] distance = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            distance[i][0] = Integer.MAX_VALUE;
            distance[i][1] = Integer.MAX_VALUE;
        }
        distance[x][0] = 0;
        distance[y][1] = 0;
        // 小美
        solve(distance, x, 0);
        // 小团
        solve(distance, y, 1);
        // 小美追小团
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i][0] > distance[i][1]) {
                ans = Math.max(ans, distance[i][0]);
            }
        }
        System.out.print(ans);
    }

    private static void solve(int[][] distance, int start, int index) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int currPoint = queue.poll();
            int next = distance[currPoint][index] + 1;
            List<Integer> list = graph[currPoint];
            for (int i = 0; list != null && i < list.size(); i++) {
                int nextPoint = list.get(i);
                if (distance[nextPoint][index] > next) {
                    distance[nextPoint][index] = next;
                    queue.add(nextPoint);
                }
            }
        }
    }
}
