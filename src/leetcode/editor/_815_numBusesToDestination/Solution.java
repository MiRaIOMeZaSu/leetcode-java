package leetcode.editor._815_numBusesToDestination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    HashMap<Integer, List<int[]>> map = new HashMap<>();
    HashMap<Integer, Integer> dp = new HashMap<>();
    int targetPos;

    public int numBusesToDestination(int[][] routes, int source, int target) {
        // 实时更新相应站点的所有趟次
        targetPos = target;
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (!map.containsKey(routes[i][j])) {
                    map.put(routes[i][j], new ArrayList<>());
                }
                map.get(routes[i][j]).add(routes[i]);
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
        List<int[]> arr = map.get(curr);
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(i).length; j++) {
                int num = arr.get(i)[j];
                if (!dp.containsKey(num) || destinationNum > dp.get(num)) {
                    dp.put(num, destinationNum);
                    solve(num, destinationNum + 1);
                }
            }
        }
    }
}