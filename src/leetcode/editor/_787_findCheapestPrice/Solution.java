package leetcode.editor._787_findCheapestPrice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    List<int[]>[] lists;
    int[][] dp;
    int src, k;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        this.src = src;
        k++;
        this.k = k;
        // 动态规划
        dp = new int[n][k + 1];
        // 拓扑排序
        // 没有重复
        // 直接深度遍历而不是拓扑排序
        lists = new ArrayList[n];
        int[] count = new int[n];
        for (int i = 0; i < flights.length; i++) {
            int[] temp = flights[i];
            if (lists[temp[0]] == null) {
                lists[temp[0]] = new ArrayList<>();
            }
            lists[temp[0]].add(temp);
        }
        if (lists[src] == null) {
            return -1;
        }
        for (int i = 0; i < lists[src].size(); i++) {
            int[] temp = lists[src].get(i);
            solve(temp[1], 1, temp[2]);
        }
        return getResult(dp[dst], k);
    }

    private void solve(int pivot, int k, int curr) {
        if (k > this.k) {
            return;
        }
        if (dp[pivot][k] != 0 && dp[pivot][k] <= curr) {
            return;
        }
        dp[pivot][k] = curr;
        if (lists[pivot] == null) {
            return;
        }
        for (int i = 0; i < lists[pivot].size(); i++) {
            int[] temp = lists[pivot].get(i);
            solve(temp[1], k + 1, curr + temp[2]);
        }
    }

    private int getResult(int[] dp, int k) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            if (dp[i] == 0) {
                continue;
            }
            result = Math.min(result, dp[i]);
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findCheapestPrice(3,
                new int[][]{{0, 1, 2}, {1, 2, 1}, {2, 0, 10}},
                1,
                2,
                1
        ));
    }
}