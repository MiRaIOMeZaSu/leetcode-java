package leetcode.editor.P4017;

import java.util.ArrayList;
import java.util.Scanner;

class Solution {
    ArrayList<Integer>[] graph;
    int heads[];

    Solution(ArrayList<Integer>[] graph, int[] heads) {
        this.graph = graph;
        this.heads = heads;
    }

    public int DFS(int current) {
        if (graph[current].size() == 0) {
            // 直到生产者时退出
            return 1;
        }
        int count = 0;
        for (int i = 0; i < graph[current].size(); i++) {
            count += DFS(graph[current].get(i));
        }
        return count;
    }

    public int solve() {
        int n = 0;
        int count = 0;
        while (heads[n] != 0) {
            count += DFS(heads[n]);
            n++;
        }
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        // 使用回溯法?拓扑排序?
        Scanner cin = new Scanner(System.in);
        int V = cin.nextInt();
        int E = cin.nextInt();
        ArrayList<Integer> graph[] = new ArrayList[V + 1];
        int in[] = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            int A = cin.nextInt();
            int B = cin.nextInt();
            graph[A].add(B);
            in[B]++;
        }
        int heads[] = new int[V + 1];
        int n = 0;
        for (int i = 1; i < V + 1; i++) {
            if (in[i] == 0) {
                heads[n] = i;
                n++;
            }
        }
        Solution solution = new Solution(graph, heads);
        int count = solution.solve();
        System.out.printf(String.valueOf(count));
    }
}
