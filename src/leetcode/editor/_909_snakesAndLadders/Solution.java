package leetcode.editor._909_snakesAndLadders;

import java.util.HashMap;

class Solution {
    int N;
    HashMap<Integer, int[]> reflect = new HashMap<>();
    int[][] dp;

    public int snakesAndLadders(int[][] board) {
        // 回溯法
        // 动态规划
        // 优先寻找蛇和梯子
        N = board.length;
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[N - 1][0] = 0;
        int pivot = N * N;
        for (int i = 1; i <= pivot; i++) {
            int[] pos = getTarget(i);
            if (board[pos[0]][pos[1]] > i) {
                int[] target = getTarget(board[pos[0]][pos[1]]);
                dp[target[0]][target[1]] = Math.min(dp[pos[0]][pos[1]], dp[target[0]][target[1]]);
            }
            for (int j = i + 1; j < i + 6 && j <= pivot; j++) {
                solve(j, dp[pos[0]][pos[1]] + 1);
            }
        }
        return dp[0][0];
    }

    private void solve(int target, int num) {
        int[] pos = getTarget(target);
        if (num >= dp[pos[0]][pos[1]]) {
            return;
        }
        dp[pos[0]][pos[1]] = num;
    }

    private int[] getTarget(int index) {
        if (reflect.containsKey(index)) {
            return reflect.get(index);
        }
        int[] res = new int[2];
        int count = index / N;
        res[0] = N - count;
        res[1] = index % N;
        if (res[1] != 0) {
            res[0]--;
            if (count % 2 != 0) {
                res[1] = N - res[1];
            } else {
                res[1]--;
            }
        } else {
            if (count % 2 != 0) {
                res[1] = N - 1;
            }
        }
        reflect.put(index, res);
        return res;
    }
}