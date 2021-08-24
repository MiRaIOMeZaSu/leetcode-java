package leetcode.editor._787_findCheapestPrice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    int limit = 100000;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 动态规划
        k++;
        int[][] dp = new int[n][k + 1];
        // 拓扑排序
        // 没有重复
        List<int[]>[] lists = new ArrayList[n];
        int[] count = new int[n];
        for (int i = 0; i < flights.length; i++) {
            int[] temp = flights[i];
            if (lists[temp[0]] == null) {
                lists[temp[0]] = new ArrayList<>();
            }
            count[temp[1]]++;
            lists[temp[0]].add(temp);
        }
        int result = Integer.MAX_VALUE;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(src);
        while (!queue.isEmpty()) {
            int pivot = queue.poll();
            for (int i = 0; i < lists[pivot].size(); i++) {
                int[] temp = lists[pivot].get(i);
                count[temp[1]]--;
                for (int j = 1; j <= k; j++) {
                    if (dp[temp[1]][j] == 0) {
                        dp[temp[1]][j] = limit;
                    }
                    if (dp[temp[0]][j - 1] == 0) {
                        dp[temp[0]][j - 1] = limit;
                        if (temp[0] == src && j - 1 == 0) {
                            dp[temp[0]][j - 1] = 0;
                        }
                    }
                    dp[temp[1]][j] = Math.min(dp[temp[1]][j], dp[temp[0]][j - 1] + temp[2]);
                }
                if (count[temp[1]] == 0) {
                    if (temp[1] == dst) {
                        return getResult(dp[dst], k);
                    }
                    queue.add(temp[1]);
                }
            }
        }
        return getResult(dp[dst], k);
    }

    int getResult(int[] dp, int k) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            if (dp[k] == 0) {
                continue;
            }
            result = Math.min(result, dp[k]);
        }
        return result > 10000 ? -1 : result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findCheapestPrice(3,
                new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}},
                0,
                2,
                1
        );
    }
}