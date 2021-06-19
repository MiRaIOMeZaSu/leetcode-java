package leetcode.editor._54_spiralOrder;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // 设定上下左右的界限
        List<Integer> ret = new ArrayList<>();
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int m, n;
        while (true) {
            n = top;
            for (int i = left; i <= right; i++) {
                ret.add(matrix[n][i]);
            }
            top++;
            if (top > bottom) {
                break;
            }
            m = right;
            for (int i = top; i <= bottom; i++) {
                ret.add(matrix[i][m]);
            }
            right--;
            if (left > right) {
                break;
            }
            n = bottom;
            for (int i = right; i >= left; i--) {
                ret.add(matrix[n][i]);
            }
            bottom--;
            if (top > bottom) {
                break;
            }
            m = left;
            for (int i = bottom; i >= top; i--) {
                ret.add(matrix[i][m]);
            }
            left++;
            if (left > right) {
                break;
            }
        }
        return ret;
    }
}