package leetcode.editor.leetcode_cup._3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

class Solution {
    char white = 'O';
    char black = 'X';
    char blank = '.';
    int result = 0;
    int[][] item = new int[][]{
            {1, 0},
            {0, 1},
            {1, 1},
            {1, -1}
    };
    Set<String> set = new HashSet<>();
    char[][] board;
    int m, n;

    public int flipChess(String[] chessboard) {
        // 当前未形成可翻转局面!
        // 并不是简单的重复区域,需要考虑连锁反应
        // 8*8=64
        // 落子必须为白色棋子的相邻位点(触发器?)
        // 暴力法,回溯法?
        m = chessboard.length;
        n = chessboard[0].length();
        board = new char[m][n];
        setBoard(chessboard);
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[0].length(); j++) {
                if (chessboard[i].charAt(j) == white) {
                    solve(i, j, chessboard);
                }
            }
        }
        return result;
    }

    private void setBoard(String[] chessboard) {
        for (int i = 0; i < chessboard.length; i++) {
            board[i] = chessboard[i].toCharArray();
        }
    }

    private void solve(int y, int x, String[] chessboard) {
        // 所有相邻非空点
        String key = y + "," + x;
        if (set.contains(key)) {
            return;
        }
        set.add(key);
        for (int i = 0; i < item.length; i++) {
            int pivot = 1;
            for (int j = 0; j < 2; j++) {
                int nextY = y + item[i][0] * pivot;
                int nextX = x + item[i][1] * pivot;
                if (isLegal(nextY, nextX) && board[nextY][nextX] == blank) {
                    board[nextY][nextX] = white;
                    result = Math.max(result, search(nextY, nextX) - 1);
                    board[nextY][nextX] = blank;
                    setBoard(chessboard);
                }
                pivot *= -1;
            }
        }
    }

    private int search(int y, int x) {
        // y,x为落子点位
        int ret = 0;
        if (board[y][x] == white) {
            ret += 1;
            board[y][x] = black;
        } else {
            return 0;
        }
        for (int i = 0; i < item.length; i++) {
            int pivot = 1;
            for (int j = 0; j < 2; j++) {
                int nextY = y + item[i][0] * pivot;
                int nextX = x + item[i][1] * pivot;
                int count = 0;
                while (isLegal(nextY, nextX) && board[nextY][nextX] == white) {
                    nextY += item[i][0] * pivot;
                    nextX += item[i][1] * pivot;
                    count++;
                }
                if (isLegal(nextY, nextX) && board[nextY][nextX] == black) {
                    // 此时有若干棋子被翻转
                    nextY -= item[i][0] * pivot;
                    nextX -= item[i][1] * pivot;
                    for (int s = 0; s < count; s++) {
                        ret += search(nextY, nextX);
                    }
                }
                pivot *= -1;
            }
        }
        return ret;
    }

    private boolean isLegal(int nextY, int nextX) {
        return nextY >= 0 && nextY < m && nextX >= 0 && nextX < n;
    }
}