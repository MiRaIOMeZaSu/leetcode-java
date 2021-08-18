package leetcode.editor._1583_unhappyFriends;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                map[i][preferences[i][j]] = j;
            }
        }
        int[] list = new int[n];
        for (int i = 0; i < pairs.length; i++) {
            list[pairs[i][0]] = pairs[i][1];
            list[pairs[i][1]] = pairs[i][0];
        }
        int result = 0;
        for (int i = 0; i < pairs.length; i++) {
            for (int j = 0; j < 2; j++) {
                int a = pairs[i][j];
                int b = pairs[i][(j + 1) % 2];
                int index = map[a][b];
                for (int x = index - 1; x >= 0; x--) {
                    int c = preferences[a][x];
                    int d = list[c];
                    if (map[c][d] > map[c][a]) {
                        result += 1;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.unhappyFriends(4, new int[][]{{1, 2, 3}, {3, 2, 0}, {3, 1, 0}, {1, 2, 0}},
                new int[][]{{0, 1}, {2, 3}});
        System.out.println(ret);
    }
}