package leetcode.editor.contest._5941_findAllPeople;

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<Integer> ans = new ArrayList<>();
    int[] time;
    List<int[]>[] graph;

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // 使用遍历
        // 存储专家知道秘密的最小时间
        time = new int[n];
        // 假设初始时间为1
        // 会议是双向的!
        time[0] = 1;
        graph = new ArrayList[n];
        graph[0] = new ArrayList<>();
        graph[0].add(new int[]{0, firstPerson, 1});
        for (int i = 0; i < meetings.length; i++) {
            if (graph[meetings[i][0]] == null) {
                graph[meetings[i][0]] = new ArrayList<>();
            }
            if (graph[meetings[i][1]] == null) {
                graph[meetings[i][1]] = new ArrayList<>();
            }
            graph[meetings[i][0]].add(meetings[i]);
            graph[meetings[i][1]].add(meetings[i]);
        }
        // 开始循环
        solve(0);
        for (int i = 0; i < n; i++) {
            if (time[i] > 0) {
                ans.add(i);
            }
        }
        return ans;
    }

    private void solve(int startPerson) {
        for (int i = 0; graph[startPerson] != null && i < graph[startPerson].size(); i++) {
            int[] meeting = graph[startPerson].get(i);
            if (meeting[0] == 28 || meeting[1] == 28) {
                System.out.println("!");
            }
            int min = Math.min(time[meeting[0]], time[meeting[1]]);
            int max = Math.max(time[meeting[0]], time[meeting[1]]);
            if (min == 0) {
                min = max;
            }
            if (meeting[2] + 1 < min) {
                // 开会时间太早,没用
                continue;
            }
            if (time[meeting[0]] == time[meeting[1]]) {
                continue;
            }
            if (time[meeting[1]] == 0) {
                time[meeting[1]] = meeting[2] + 1;
                solve(meeting[1]);
                continue;
            } else if (time[meeting[0]] == 0) {
                time[meeting[0]] = meeting[2] + 1;
                solve(meeting[0]);
                continue;
            }
            // 此时开始寻找谁给谁同步的情况(两者都有初始时间)
            if (meeting[2] + 1 < max) {
                // 可以起到更新的效果
                if (time[meeting[1]] > meeting[2] + 1) {
                    time[meeting[1]] = meeting[2] + 1;
                    solve(meeting[1]);
                } else {
                    time[meeting[0]] = meeting[2] + 1;
                    solve(meeting[0]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findAllPeople(4, new int[][]{{3, 1, 3}, {1, 2, 2}, {0, 3, 3}}, 3));
    }
}