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
            graph[meetings[i][0]].add(meetings[i]);
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
            if (meeting[2] + 1 >= time[startPerson]) {
                if (time[meeting[1]] == 0 || time[meeting[1]] > meeting[2] + 1) {
                    time[meeting[1]] = meeting[2] + 1;
                    solve(meeting[1]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findAllPeople(6, new int[][]{{0, 2, 1}, {1, 3, 1}, {4, 5, 1}}, 1));
    }
}