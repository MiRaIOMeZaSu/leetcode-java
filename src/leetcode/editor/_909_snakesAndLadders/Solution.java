package leetcode.editor._909_snakesAndLadders;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

class Solution {
    int N;
    HashMap<Integer, int[]> reflect = new HashMap<>();
    Deque<Integer> q = new LinkedList<>();
    int[][][] dp;

    public int snakesAndLadders(int[][] board) {
        // 回溯法
        // 动态规划
        // 优先寻找蛇和梯子
        // 蛇和梯子的传送是强制的
        // 蛇和梯子的使用应该表示出来
        N = board.length;
        dp = new int[N][N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int x = 0; x < 2; x++) {
                    dp[i][j][x] = Integer.MAX_VALUE;
                }
            }
        }
        dp[N - 1][0][0] = 0;
        int pivot = N * N;
        for (int i = 1; i <= pivot; i++) {
            q.add(i);
        }
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            int[] pos = getTarget(i);
            if (board[pos[0]][pos[1]] != -1) {
                // 强制跳转
                int[] target = getTarget(board[pos[0]][pos[1]]);
                if (dp[pos[0]][pos[1]][0] < dp[target[0]][target[1]][1]) {
                    dp[target[0]][target[1]][1] = dp[pos[0]][pos[1]][0];
                    q.push(board[pos[0]][pos[1]]);
                }
            }
            if (board[pos[0]][pos[1]] == -1 || dp[pos[0]][pos[1]][1] != Integer.MAX_VALUE) {
                // 到此不需要强制跳转
                int stop = 0;
                if (board[pos[0]][pos[1]] != -1) {
                    stop++;
                }
                for (int j = i + 1; j <= i + 6 && j <= pivot; j++) {
                    solve(j, pos, stop);
                }
            }
        }

        int[] endPos = getTarget(pivot);
        int res = Math.min(dp[endPos[0]][endPos[1]][0], dp[endPos[0]][endPos[1]][1]);
        return res != Integer.MAX_VALUE ? res : -1;
    }

    private void solve(int target, int[] sourcePos, int stop) {
        int[] pos = getTarget(target);
        int temp = dp[pos[0]][pos[1]][0];
        for (int i = 1; i >= stop; i--) {
            if (dp[sourcePos[0]][sourcePos[1]][i] == Integer.MAX_VALUE) {
                continue;
            }
            dp[pos[0]][pos[1]][0] = Math.min(dp[sourcePos[0]][sourcePos[1]][i] + 1, dp[pos[0]][pos[1]][0]);
        }
        if (dp[pos[0]][pos[1]][0] != temp) {
            q.push(target);
        }
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