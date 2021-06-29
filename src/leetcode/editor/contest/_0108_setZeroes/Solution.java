package leetcode.editor.contest._0108_setZeroes;

class Solution {
    public void setZeroes(int[][] matrix) {
        boolean flagI = false;
        boolean flagJ = false;
        int m = matrix.length;
        int n = matrix[0].length;
        if (matrix[0][0] == 0) {
            flagI = true;
            flagJ = true;
        } else {
            for (int i = 1; i < m; i++) {
                if (matrix[i][0] == 0) {
                    flagJ = true;
                    break;
                }
            }
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == 0) {
                    flagI = true;
                    break;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (flagJ) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (flagI) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }
}