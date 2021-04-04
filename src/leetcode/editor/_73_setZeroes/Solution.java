package leetcode.editor._73_setZeroes;

class Solution {
    int[][] nums;
    public void setZeroes(int[][] matrix) {
        // 将1改成2,最后才改成3
        nums = matrix;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    solve(i, j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 2) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private void solve(int i, int j) {
        for (int x = 0; x < nums.length; x++) {
            if (nums[x][j] == 1) {
                nums[x][j] = 2;
            }
        }
        for (int y = 0; y < nums[0].length; y++) {
            if (nums[i][y] == 1) {
                nums[i][y] = 2;
            }
        }
    }
}