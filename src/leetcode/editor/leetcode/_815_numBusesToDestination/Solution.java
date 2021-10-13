package leetcode.editor._815_numBusesToDestination;

import java.util.*;

class Solution {
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    Set<Integer> visit = new HashSet<>();
    int targetPos;
    int[][] graph;
    boolean[] routesUsed;

    public int numBusesToDestination(int[][] routes, int source, int target) {
        // 实时更新相应站点的所有趟次
        // 应该通过routes建立连接,可以从任意一边斩断两边的联系
        graph = routes;
        routesUsed = new boolean[routes.length];
        targetPos = target;
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (!map.containsKey(routes[i][j])) {
                    map.put(routes[i][j], new ArrayList<>());
                }
                map.get(routes[i][j]).add(i);
            }
        }
        visit.add(source);
        // 应该使用广度优先算法
        int index = 0;
        List<Integer> curr, next;
        curr = new LinkedList<>();
        curr.add(source);
        while (!curr.isEmpty() && !visit.contains(target)) {
            next = new ArrayList<>();
            for (int i = 0; i < curr.size(); i++) {
                solve(curr.get(i), next);
            }
            curr = next;
            index++;
        }

        if (!visit.contains(target)) {
            return -1;
        }
        return index;
    }

    private void solve(int curr, List<Integer> res) {
        Set<Integer> next = new HashSet<>();
        List<Integer> list = map.get(curr);
        for (int i = 0; i < list.size(); i++) {
            int index = list.get(i);
            if (routesUsed[index]) {
                continue;
            }
            routesUsed[index] = true;
            int[] arr = graph[index];
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j];
                if (!visit.contains(num) && !next.contains(num)) {
                    next.add(num);
                    res.add(num);
                    visit.add(num);
                }
            }
        }
    }
}