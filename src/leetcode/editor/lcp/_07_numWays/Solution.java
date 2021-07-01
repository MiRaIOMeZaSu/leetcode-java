package leetcode.editor.lcp._07_numWays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    int pivot;
    int res = 0;
    int target;
    List<Integer>[] list;

    public int numWays(int n, int[][] relation, int k) {
        // 玩家编号小于等于10,可以使用状压法
        // 可以重复经过一个人,但传递方向是固定的
        list = new ArrayList[n];
        pivot = k;
        target = n - 1;
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < relation.length; i++) {
            list[relation[i][0]].add(relation[i][1]);
        }
        solve(0, 0);
        return res;
    }

    private void solve(int index, int next) {
        // 使用回溯法,状压并不必要
        if (index > pivot) {
            return;
        } else if (index == pivot) {
            if (next == target) {
                res++;
            }
            return;
        }
        for (int i = 0; i < list[next].size(); i++) {
            int num = list[next].get(i);
            solve(index + 1, num);
        }
    }
}