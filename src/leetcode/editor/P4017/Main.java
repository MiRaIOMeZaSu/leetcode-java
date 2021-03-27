package leetcode.editor.P4017;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 使用回溯法?拓扑排序?
        Scanner cin = new Scanner(System.in);
        int V = cin.nextInt();
        int E = cin.nextInt();
        ArrayList<Integer> graph[] = new ArrayList[V + 1];
        ArrayList<Integer> backup[] = new ArrayList[V + 1];
        int in[] = new int[V + 1];
        int out[] = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
            backup[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            int A = cin.nextInt();
            int B = cin.nextInt();
            graph[B].add(A);
            backup[B].add(A);
            in[B]++;
            out[A]++;
        }
        // 使用拓扑排序
        List<Integer> queue = new ArrayList<>();
        Queue<Integer> zeroIn = new LinkedList<>();
        List<Integer> ends = new ArrayList<>();
        List<Integer> heads = new ArrayList<>();
        for (int i = 1; i < V + 1; i++) {
            if (in[i] == 0) {
                zeroIn.add(i);
                heads.add(i);
            }
            if (out[i] == 0) {
                ends.add(i);
            }
        }
        int visit[] = new int[V + 1];
        while (!zeroIn.isEmpty()) {
            int pivot = zeroIn.poll();
            queue.add(pivot);
            for (int i = 1; i < V + 1; i++) {
                if (graph[i].indexOf(pivot) != -1) {
                    graph[i].remove(graph[i].get(graph[i].indexOf(pivot)));
                    if (graph[i].size() == 0 && visit[i] == 0) {
                        visit[i] = 1;
                        zeroIn.add(i);
                    }
                }
            }
        }
        long dp[] = new long[V + 1];
        for (int i : heads) {
            dp[i] = 1;
        }
        for (int i : queue) {
            if (dp[i] == 0) {
                for (int n : backup[i]) {
                    dp[i] += dp[n];
                    dp[i] %= 80112002;
                }
            }
        }
        int count = 0;
        for (int i : ends) {
            count += dp[i];
            count %= 80112002;
        }
        System.out.printf(String.valueOf(count % 80112002));
    }
}
