package leetcode.editor.leetcode_cup._4;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int circleGame(int[][] toys, int[][] circles, int r) {
        int[][] toyR = new int[10001][10001];
        for (int i = 0; i < toys.length; i++) {
            toyR[toys[i][1]][toys[i][1]] = toys[i][2];
        }
        for (int i = 0; i < circles.length; i++) {
            // 一个正方形?
            for (int y = circles[i][0] - r + 1; y < circles[i][0] + r; y++) {
                for (int x = circles[i][1] - r + 1; x < circles[i][1] + r; x++) {
                    // 点在园内
                    if (toyR[y][x] <= 0) {
                        continue;
                    }
                    if (Math.pow(y - circles[i][0], 2) + Math.pow(x - circles[i][1], 2) > Math.pow(r, 2)) {
                        continue;
                    }

                }
            }
        }
        int result = 0;
        for (int i = 0; i < toys.length; i++) {
            if (toyR[toys[i][1]][toys[i][1]] == -1) {
                result += 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.circleGame(new int[][]{{3, 3, 1}, {3, 2, 1}}, new int[][]{{4, 3}}, 2);
    }
}