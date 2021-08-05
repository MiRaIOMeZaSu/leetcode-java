package leetcode.editor._802_eventualSafeNodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

class Solution {
    List<Integer> result = new ArrayList<>();
    List<Integer>[] in;
    int[] out;
    int[][] graph;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        this.graph = graph;
        int size = graph.length;
        out = new int[size];
        in = new ArrayList[size];
        // 反过来寻找
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                // 有i入了
                int index = graph[i][j];
                if (in[index] == null) {
                    in[index] = new ArrayList<>();
                }
                in[index].add(i);
            }
            out[i] = graph[i].length;
        }

        for (int i = 0; i < size; i++) {
            if (graph[i].length == 0) {
                solve(i);
            }
        }
        result.sort(Comparator.naturalOrder());
        return result;
    }

    private void solve(int i) {
        result.add(i);
        if (in[i] == null) {
            return;
        }
        for (int j = 0; j < in[i].size(); j++) {
            int start = in[i].get(j);
            out[start]--;
            if (out[start] == 0 && graph[start].length != 0) {
                solve(start);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> ret = solution.eventualSafeNodes(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}});
        for (int i : ret
        ) {
            System.out.println(i);
        }
    }
}