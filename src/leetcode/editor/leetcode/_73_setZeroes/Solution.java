package leetcode.editor._73_setZeroes;

class Solution {
    public void setZeroes(int[][] matrix) {
        // 对于O(m + n)的算法,存储了被设置为零的行列
        // 要求使用原地算法
        int m = matrix.length;
        int n = matrix[0].length;
        boolean col[] = new boolean[n];
        boolean row[] = new boolean[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (col[j] || row[i]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}