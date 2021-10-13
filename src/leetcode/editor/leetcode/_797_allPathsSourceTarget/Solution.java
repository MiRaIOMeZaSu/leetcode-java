package leetcode.editor._797_allPathsSourceTarget;

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<List<Integer>> lists;
    int n;
    int[][] graph;
    int[] bits = new int[16];

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        bits[1] = 1;
        for (int i = 2; i < 16; i++) {
            bits[i] = bits[i - 1] * 2;
        }
        this.n = graph.length;
        this.graph = graph;
        lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(0);
        solve(0, list);
        return lists;
    }

    public void solve(int visit, List<Integer> list) {
        int index = list.size() - 1;
        int last = list.get(index);
        if (last == n - 1) {
            lists.add(new ArrayList<>(list));
            return;
        }
        // 还没到时
        for (int i = 0; i < graph[last].length; i++) {
            int next = graph[last][i];
            int bit = bits[next];
            if ((visit | bit) != visit) {
                list.add(next);
                solve(visit | bit, list);
                list.remove(index + 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.allPathsSourceTarget(new int[][]{

        });
    }
}