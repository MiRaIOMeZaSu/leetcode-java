package leetcode.editor.P4017;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 使用回溯法?拓扑排序?
        Scanner cin = new Scanner(System.in);
        int V = cin.nextInt();
        int E = cin.nextInt();
        ArrayList<Integer> graph[] = new ArrayList[V + 1];
        int in[] = new int[V + 1];
        int out[] = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            int A = cin.nextInt();
            int B = cin.nextInt();
            graph[B].add(A);
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
        int dp[] = new int[V + 1];
        for (int i : heads) {
            dp[i] = 1;
        }
        int visit[] = new int[V + 1];
        while (!zeroIn.isEmpty()) {
            int pivot = zeroIn.poll();
            queue.add(pivot);
            if (dp[pivot] == 0) {
                for (int n : graph[pivot]) {
                    dp[pivot] += dp[n];
                    dp[pivot] %= 80112002;
                }
            }
            for (int i = 1; i < V + 1; i++) {
                if (graph[i].indexOf(pivot) != -1) {
                    in[i]--;
                    if (in[i] == 0 && visit[i] == 0) {
                        visit[i] = 1;
                        zeroIn.add(i);
                    }
                }
            }
        }
        int count = 0;
        for (int i : ends) {
            count += dp[i];
            count %= 80112002;
        }
        System.out.printf(String.valueOf(count));
    }
}
