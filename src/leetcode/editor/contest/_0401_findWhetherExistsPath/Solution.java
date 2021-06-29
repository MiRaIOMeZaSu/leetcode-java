package leetcode.editor.contest._0401_findWhetherExistsPath;

import java.util.*;

class Solution {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        // 拓扑排序
        // 有向图
        List<Integer>[] nums = new ArrayList[n];
        for (int i = 0; i < graph.length; i++) {
            if(nums[graph[i][0]]==null){
                nums[graph[i][0]] = new ArrayList<>();
            }
            if(nums[graph[i][1]]==null){
                nums[graph[i][1]] = new ArrayList<>();
            }
            nums[graph[i][0]].add(graph[i][1]);
        }
        Set<Integer> visit = new HashSet<>();
        Deque<Integer> q = new LinkedList<>();
        q.add(start);
        visit.add(start);
        while (!q.isEmpty() && !visit.contains(target)) {
            int pivot = q.poll();
            for (int i = 0; i < nums[pivot].size(); i++) {
                int num = nums[pivot].get(i);
                if (!visit.contains(num)) {
                    q.add(num);
                    visit.add(num);
                }
            }
        }
        if(visit.contains(target)){
            return true;
        }
        return false;
    }
}