package leetcode.editor._36_isValidSudoku;

import java.util.HashMap;

class Solution {
    static final char pivot = '1';

    public boolean isValidSudoku(char[][] board) {
        // 先判断3宫格内的再依行依列遍历
        // 9宫格的大小是固定的
        for (int startI = 0; startI <= 9 - 3; startI += 3) {
            for (int startJ = 0; startJ <= 9 - 3; startJ += 3) {
                boolean[] map = new boolean[9];
                for (int i = startI; i < startI + 3; i++) {
                    for (int j = startJ; j < startJ + 3; j++) {
                        int key = board[i][j] - pivot;
                        if (key < 0 || key > 9) {
                            continue;
                        }
                        if (map[key]) {
                            return false;
                        }
                        map[key] = true;
                    }
                }
            }
        }
        boolean[] map;
        for (int i = 0; i < 9; i++) {
            // 行
            map = new boolean[9];
            for (int j = 0; j < 9; j++) {
                int key = board[i][j] - pivot;
                if (key < 0 || key > 9) {
                    continue;
                }
                if (map[key]) {
                    return false;
                }
                map[key] = true;
            }
            map = new boolean[9];
            for (int j = 0; j < 9; j++) {
                int key = board[j][i] - pivot;
                if (key < 0 || key > 9) {
                    continue;
                }
                if (map[key]) {
                    return false;
                }
                map[key] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean ret = solution.isValidSudoku(new char[][]{
                {'.', '.', '.', '.', '.', '.', '5', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'9', '3', '.', '.', '2', '.', '4', '.', '.'},
                {'.', '.', '7', '.', '.', '.', '3', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '3', '4', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '3', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '5', '2', '.', '.'}});
        System.out.println(ret);
    }
}