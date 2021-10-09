package leetcode.editor._52_totalNQueens;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution {
    int ans = 0;
    boolean[] rowVisit;
    boolean[][] slashVisit;
    int globalN;
    Deque<int[]> test = new LinkedList<>();
    public int totalNQueens(int n) {
        // 经典而简单的回溯法
        // 重点是判断不满足条件的方法
        // 同样不能在同一条斜线上,只存储斜线的左上头部
        slashVisit = new boolean[n][n];
        globalN = n;
        rowVisit = new boolean[n];
        // 只需返回存储的数量
        solve(n);
        return ans;
    }

    private void solve(int n) {
        if (n == 0) {
            ans++;
            return;
        }
        for (int i = 0; i < globalN; i++) {
            int currCol = globalN - n;
            int currRow = i;
            if (!rowVisit[i]) {
                boolean flag = currCol <= currRow;
                int nCurrCol = flag ? 0 : currCol - currRow;
                int nCurrRow = flag ? currRow - currCol : 0;
                if (!slashVisit[nCurrCol][nCurrRow]) {
                    // 此棋子可以落子
                    rowVisit[i] = true;
                    slashVisit[nCurrCol][nCurrRow] = true;
                    test.push(new int[]{currCol,currRow});
                    solve(n - 1);
                    rowVisit[i] = false;
                    test.pop();
                    slashVisit[nCurrCol][nCurrRow] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.totalNQueens(4);
    }
}