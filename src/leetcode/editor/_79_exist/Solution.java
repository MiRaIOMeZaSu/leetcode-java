package leetcode.editor._79_exist;

class Solution {
    char[][] chars;
    boolean[][] visited;
    String sentence;

    public boolean exist(char[][] board, String word) {
        // 使用回溯法
        chars = board;
        sentence = word;
        char start = word.charAt(0);
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == start) {
                    visited[i][j] = true;
                    if (solve(i, j, 1)) {
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    private boolean solve(int i, int j, int index) {
        if (index >= sentence.length()) {
            return true;
        }
        boolean ret = false;
        if (i + 1 < chars.length) {
            if (chars[i + 1][j] == sentence.charAt(index) && !visited[i + 1][j]) {
                visited[i + 1][j] = true;
                ret = ret || solve(i + 1, j, index + 1);
                visited[i + 1][j] = false;
            }
        }
        if (i - 1 >= 0 && !ret) {
            if (chars[i - 1][j] == sentence.charAt(index) && !visited[i - 1][j]) {
                visited[i - 1][j] = true;
                ret = ret || solve(i - 1, j, index + 1);
                visited[i - 1][j] = false;
            }
        }
        if (j + 1 < chars[0].length && !ret) {
            if (chars[i][j + 1] == sentence.charAt(index) && !visited[i][j + 1]) {
                visited[i][j + 1] = true;
                ret = ret || solve(i, j + 1, index + 1);
                visited[i][j + 1] = false;
            }
        }
        if (j - 1 >= 0 && !ret) {
            if (chars[i][j - 1] == sentence.charAt(index) && !visited[i][j - 1]) {
                visited[i][j - 1] = true;
                ret = ret || solve(i, j - 1, index + 1);
                visited[i][j - 1] = false;
            }
        }
        return ret;
    }
}