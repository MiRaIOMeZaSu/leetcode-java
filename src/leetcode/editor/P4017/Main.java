package leetcode.editor.P4017;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 使用回溯法?拓扑排序?
        // 使用邻接矩阵
        Scanner cin = new Scanner(System.in);
        int V = cin.nextInt();
        int E = cin.nextInt();
        List<Short>[] graph = new ArrayList[V + 1];
        short[] in = new short[V + 1];
        short[] out = new short[V + 1];
        for (int i = 1; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            short A = cin.nextShort();
            short B = cin.nextShort();
            graph[B].add(A);
            in[B]++;
            out[A]++;
        }
        // 使用拓扑排序
        Queue<Short> zeroIn = new LinkedList<>();
        List<Short> heads = new ArrayList<>();
        for (short i = 1; i < V + 1; i++) {
            if (in[i] == 0) {
                zeroIn.add(i);
                heads.add(i);
            }
        }
        int[] dp = new int[V + 1];
        for (short i : heads) {
            dp[i] = 1;
        }
        short[] visit = new short[V + 1];
        int count = 0;
        while (!zeroIn.isEmpty()) {
            short pivot = zeroIn.poll();
            if (dp[pivot] == 0) {
                for (int n : graph[pivot]) {
                    dp[pivot] += dp[n];
                    dp[pivot] %= 80112002;
                }
                if (out[pivot] == 0) {
                    count += dp[pivot];
                    count %= 80112002;
                }
            }
            for (short i = 1; i < V + 1; i++) {
                if (graph[i].contains(pivot)) {
                    in[i]--;
                    if (in[i] == 0 && visit[i] == 0) {
                        visit[i] = 1;
                        zeroIn.add(i);
                    }
                }
            }
        }
        System.out.printf(String.valueOf(count % 80112002));
    }
}