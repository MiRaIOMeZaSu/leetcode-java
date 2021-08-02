package leetcode.editor._743_networkDelayTime;

import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 简单的拓扑排序
        // 先将times转化为领接表
        // 不含重复边
        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        int size = times.length;
        int[] reservse = new int[n + 1];
        for (int i = 0; i < size; i++) {
            if (graph[times[i][0]] == null) {
                graph[times[i][0]] = new ArrayList<>();
            }
            graph[times[i][0]].add(new int[]{times[i][1], times[i][2]});
            reservse[times[i][1]]++;
        }
        Set<Integer> visit = new HashSet<>();
        Deque<Integer> queue = new LinkedList<>();
        int[] time = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            time[i] = -1;
        }
        time[0] = 0;
        time[k] = 0;
        queue.add(k);
        visit.add(k);
        while (!queue.isEmpty()) {
            int pivot = queue.poll();
            if (graph[pivot] == null) {
                continue;
            }
            for (int i = 0; i < graph[pivot].size(); i++) {
                int[] next = graph[pivot].get(i);
                if (time[next[0]] == -1) {
                    time[next[0]] = next[1] + time[pivot];
                } else {
                    time[next[0]] = Math.min(next[1] + time[pivot], time[next[0]]);
                }
                reservse[next[0]]--;
                if (reservse[next[0]] <= 0 && !visit.contains(next[0])) {
                    queue.add(next[0]);
                    visit.add(next[0]);
                }
            }
        }
        int result = -1;
        for (int i = 0; i <= n; i++) {
            if (i == k) {
                continue;
            }
            if (time[i] == -1) {
                return -1;
            }
            result = Math.max(time[i], result);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.networkDelayTime(new int[][]{{3, 5, 78}, {2, 1, 1}, {1, 3, 0}, {4, 3, 59}, {5, 3, 85}, {5, 2, 22}, {2, 4, 23}, {1, 4, 43}, {4, 5, 75}, {5, 1, 15}, {1, 5, 91}, {4, 1, 16}, {3, 2, 98}, {3, 4, 22}, {5, 4, 31}, {1, 2, 0}, {2, 5, 4}, {4, 2, 51}, {3, 1, 36}, {2, 3, 59}}, 5, 5);
    }
}