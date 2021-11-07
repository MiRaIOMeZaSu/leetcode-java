package leetcode.editor.contest._5921_maximalPathQuality;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution {
    boolean[][][] mem;
    int ans = 0;
    int[] visited;
    List<int[]>[] graph;
    int[] values;
    int maxTime;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        // 要使穿越过的结点的价值和最大
        // 2000条边,1000个点
        // 要求必须能够回来
        // 出发路径和回来路径会相互影响
        // 直接遍历?
        // 记忆法
        // 当前走过路径数值,当前价值(visited[存储数量]用以辅助),当前位置
        this.maxTime = maxTime;
        this.values = values;
        int totalVal = 0;
        visited = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            totalVal += values[i];
        }
        // 使用邻接表法
        graph = new ArrayList[values.length];
        for (int i = 0; i < edges.length; i++) {
            if (graph[edges[i][0]] == null) {
                graph[edges[i][0]] = new ArrayList<>();
            }
            if (graph[edges[i][1]] == null) {
                graph[edges[i][1]] = new ArrayList<>();
            }
            graph[edges[i][0]].add(new int[]{edges[i][1], edges[i][2]});
            graph[edges[i][1]].add(new int[]{edges[i][0], edges[i][2]});
        }
        mem = new boolean[maxTime + 1][totalVal + 1][values.length];
        visited[0] = 1;
        solve(0, values[0], 0);
        return ans;
    }

    private void solve(int currTime, int currVal, int currPosition) {
        // 应该添加对数值的记忆,保证不会有在相同点位缺有更小的currVal和更大的currTime
        if (currTime > maxTime) {
            return;
        }
        if (!mem[currTime][currVal][currPosition]) {
            // 开始遍历所有的边
            if (currPosition == 0) {
                ans = Math.max(ans, currVal);
            }
            for (int i = 0; graph[currPosition] != null && i < graph[currPosition].size(); i++) {
                int[] next = graph[currPosition].get(i);
                int nextVal = currVal + (visited[next[0]] == 0 ? values[next[0]] : 0);
                visited[next[0]]++;
                solve(currTime + next[1], nextVal, next[0]);
                visited[next[0]]--;
            }
        }
    }

    public static void main(String[] args) {

    }
}