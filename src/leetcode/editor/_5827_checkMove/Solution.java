package leetcode.editor._5827_checkMove;

class Solution {
    int[][] four = new int[][]{{0, -1}, {1, 0}, {0, 1}, {-1, 0},
            {-1, -1}, {1, 1}, {-1, 1}, {1, -1}
    };

    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        // 向周围检查
        // 只要可以作为端点即可
        // 是两个端点
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < 8; i++) {
            int currY = four[i][0] + rMove;
            int currX = four[i][1] + cMove;
            int count = 1;
            char last = color;
            if (statify(m, n, currX, currY)) {
                if (board[currY][currX] == color) {
                    continue;
                }
            } else {
                continue;
            }
            do {
                if (board[currY][currX] == '.') {
                    if (count == 3) {
                        return true;
                    }
                    break;
                }
                if (last != board[currY][currX]) {
                    last = board[currY][currX];
                    count++;
                }
                if (count > 3) {
                    return true;
                }
                currY = currY + four[i][0];
                currX = currX + four[i][1];
            } while (statify(m, n, currX, currY));

            if (count >= 3) {
                return true;
            }
        }
        return false;
    }

    private boolean statify(int m, int n, int currX, int currY) {
        return currX < n && currY < m && currX >= 0 && currY >= 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.checkMove(new char[][]{
                {'B', 'W', 'B', 'B', 'W', 'W', '.', 'W'},
                {'W', 'W', 'B', 'W', 'B', 'B', 'W', '.'},
                {'B', 'W', '.', 'W', '.', '.', 'B', 'B'},
                {'.', 'B', 'W', '.', 'B', 'W', 'B', 'W'},
                {'B', 'B', 'W', 'B', '.', 'B', 'W', 'B'},
                {'B', 'B', 'B', 'W', 'B', 'W', 'W', 'B'},
                {'.', 'W', '.', 'W', '.', 'W', 'W', 'W'},
                {'.', 'W', 'W', 'B', 'B', 'B', 'B', '.'}}, 7, 7, 'B');
    }
}