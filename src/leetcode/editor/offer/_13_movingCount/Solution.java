package leetcode.editor.offer._13_movingCount;

class Solution {
    boolean[][] visit;
    int ret = 0;
    int[][] items = new int[][]{
            {0, -1}, {0, 1}, {1, 0}, {-1, 0}
    };
    int pivot;
    int _m, _n;

    public int movingCount(int m, int n, int k) {
        // 填色法
        _n = n;
        _m = m;
        pivot = k;
        visit = new boolean[m][n];
        fill(0, 0);
        return ret;
    }

    private void fill(int i, int j) {
        if (visit[i][j]) {
            return;
        }
        visit[i][j] = true;
        if (!satisfy(i, j)) {
            return;
        }
        ret++;
        for (int s = 0; s < 4; s++) {
            int y = i + items[s][0];
            int x = j + items[s][1];
            if (y >= 0 && x >= 0 && y < _m && x < _n) {
                fill(y, x);
            }
        }
    }

    private boolean satisfy(int a, int b) {
        int ret = 0;
        int[] temp = new int[]{a, b};
        for (int i = 0; i < 2; i++) {
            int num = temp[i];
            while (num != 0) {
                ret += num % 10;
                num /= 10;
            }
        }
        return ret <= pivot;
    }
}