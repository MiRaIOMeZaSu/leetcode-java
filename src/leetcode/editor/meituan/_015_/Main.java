package leetcode.editor.meituan._015_;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int[][] cost;
    private static int[][][] ab;
    private static int[][] items = new int[][]{
            {0, -1}, {0, 1}, {1, 0}, {-1, 0}
    };
    public static int n, m, xs, ys, xt, yt;

    public static void main(String[] args) throws IOException {
        // 动态规划
        // 涂色法
        // 计算到达的时间
        // 每个十字路口结点有两个属性,两个属性分别该十字路口行和列分别可走的时间
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] temp = bufferedReader.readLine().trim().split(" ");
        int index = 0;
        n = Integer.parseInt(temp[index]);
        index++;
        m = Integer.parseInt(temp[index]);
        index++;
        xs = Integer.parseInt(temp[index]);
        index++;
        ys = Integer.parseInt(temp[index]);
        index++;
        xt = Integer.parseInt(temp[index]);
        index++;
        yt = Integer.parseInt(temp[index]);
        // a 上下移动,b 左右移动
        ab = new int[2][n][m];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                temp = bufferedReader.readLine().trim().split(" ");
                for (int k = 0; k < m; k++) {
                    ab[i][j][k] = Integer.parseInt(temp[k]);
                }
            }
        }
        bufferedReader.close();
        cost = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }
        cost[xs - 1][ys - 1] = 0;
        solve(xs - 1, ys - 1);
        bufferedWriter.write(String.valueOf(cost[xt - 1][yt - 1]));
        bufferedWriter.close();
    }

    private static void solve(int i, int j) {
        // 对于i和j,找到四周
        int timePivot = cost[i][j] % (ab[0][i][j] + ab[1][i][j]);
        List<int[]> list = new ArrayList<>();
        // 上下
        int aNext = cost[i][j] + 1;
        int bNext = cost[i][j] + 1;
        if (timePivot >= ab[0][i][j]) {
            aNext += (ab[0][i][j] + ab[1][i][j]) - timePivot;
        }
        for (int k = i - 1; k <= i + 1; k += 2) {
            if (k >= 0 && k < n && cost[k][j] > aNext) {
                cost[k][j] = aNext;
                list.add(new int[]{k, j});
            }
        }
        // 左右
        if (timePivot < ab[0][i][j]) {
            bNext += ab[0][i][j] - timePivot;
        }
        for (int k = j - 1; k <= j + 1; k += 2) {
            if (k >= 0 && k < m && cost[i][k] > bNext) {
                cost[i][k] = bNext;
                list.add(new int[]{i, k});
            }
        }
        for (int k = 0; k < list.size(); k++) {
            solve(list.get(k)[0], list.get(k)[1]);
        }
    }
}
