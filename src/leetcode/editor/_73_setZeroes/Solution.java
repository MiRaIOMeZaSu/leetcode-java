package leetcode.editor._73_setZeroes;

class Solution {
    int[][] nums;
    public void setZeroes(int[][] matrix) {
        // 将1改成2,最后才改成3
        nums = matrix;
        solve(0, 0, matrix.length, matrix[0].length);
    }

    private void solve(int _i, int _j, int len_i, int len_j) {
        for (int i = _i; i < _i + len_i; i++) {
            for (int j = _j; j < _j + len_j; j++) {
                if (nums[i][j] == 0) {
                    setZero(i, j);
                    // 左下
                    solve(i + 1, _j, len_i - i - 1, j);
                    // 右下
                    solve(i + 1, j + 1, len_i - i - 1, len_j - j - 1);
                    return;
                }
            }
        }
    }

    private void setZero(int i, int j) {
        for (int x = 0; x < nums.length; x++) {
            nums[x][j] = 0;
        }
        for (int y = 0; y < nums[0].length; y++) {
            nums[i][y] = 0;
        }
    }
}