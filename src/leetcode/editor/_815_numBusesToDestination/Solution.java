package leetcode.editor._815_numBusesToDestination;

import java.util.*;

class Solution {
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    HashMap<Integer, Integer> dp = new HashMap<>();
    int targetPos;
    int[][] graph;

    public int numBusesToDestination(int[][] routes, int source, int target) {
        // 实时更新相应站点的所有趟次
        graph = routes;
        targetPos = target;
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (!map.containsKey(routes[i][j])) {
                    map.put(routes[i][j], new ArrayList<>());
                }
                map.get(routes[i][j]).add(i);
            }
        }
        dp.put(source, 0);
        solve(source, 1);
        if (!dp.containsKey(target)) {
            return -1;
        }
        return dp.get(target);
    }

    private void solve(int curr, int destinationNum) {
        if (dp.containsKey(targetPos) && dp.get(targetPos) <= destinationNum) {
            return;
        }
        List<Integer> list = map.get(curr);
        for (int i = 0; i < list.size(); i++) {
            int[] arr = graph[list.get(i)];
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j];
                if (!dp.containsKey(num) || destinationNum < dp.get(num)) {
                    dp.put(num, destinationNum);
                    solve(num, destinationNum + 1);
                }
            }
        }
    }
}