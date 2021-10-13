package leetcode.editor._51_Nqueen;
//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
//
//
//
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//
//
//
// 示例 1：
//
//
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：[["Q"]]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 9
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
//
//
//
// Related Topics 回溯算法
// 👍 766 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(int[][] matrix, int row, int col) {
        // 输入数组以验证是否满足条件
        // 每次只检测当前棋子是否能在放在棋盘中的指定位置,若每次都全盘检测,则会有多余验证
        // 前几个以满足条件的必然不在行列斜线上,若新增棋子在原有任意棋子行列斜线上,则同等与原有棋子在新增棋子的行列斜线上
        int n = matrix.length;
        // 上面
        for (int i = 0; i < row; i++) {
            if (matrix[i][col] == 1) {
                return false;
            }
        }
        // 左上
        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            if (matrix[row - i][col - i] == 1) {
                return false;
            }
        }
        // 右上
        for (int i = 1; col + i < n && row - i >= 0; i++) {
            if (matrix[row - i][col + i] == 1) {
                return false;
            }
        }
        return true;
    }

    public List<String> genString(int[][] matrix) {
        List<String> arr = new ArrayList();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 1) {
                    String str = ".".repeat(j) + "Q" + ".".repeat(matrix.length - j - 1);
                    arr.add(str);
                    break;
                } else if (j == matrix.length - 1) {
                    arr.add(".".repeat(matrix.length));
                }
            }
        }
        return arr;
    }

    public void solve(int[][] matrix, int n, int count, List<List<String>> results) {
        // count为棋子数量
        if (n == count) {
            results.add(genString(matrix));
        }
        int flag = 0;
        int row = count;
        for (int col = 0; col < n; col++) {
            if (this.isValid(matrix, row, col)) {
                int[][] array = new int[n][n];
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        array[i][j] = matrix[i][j];
                    }
                }
                array[row][col] = 1;
                this.solve(array, n, count + 1, results);
            }
            // 不符合则撤销,符合则继续
        }

    }


    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList();
        int[][] route = new int[n][n];
        this.solve(route, n, 0, results);
        return results;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
