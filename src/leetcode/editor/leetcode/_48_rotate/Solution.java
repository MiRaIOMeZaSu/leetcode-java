package leetcode.editor._48_rotate;

public class Solution {
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        for (int i = length; i > 1; i -= 2) {
            // 旋转外面长宽为i的壳子
            for (int n = 0; n < i - 1; n++) {
                // 对4个数字进行交换
                // d a
                // c b
                int out = (length - i) / 2;
                int a = matrix[out][out + i - 1 - n];
                int b = matrix[out + i - 1 - n][out + i - 1];
                int c = matrix[out + i - 1][out + n];
                int d = matrix[n + out][out];
                matrix[out][out + i - 1 - n] = d;
                matrix[out + i - 1 - n][out + i - 1] = a;
                matrix[out + i - 1][out + n] = b;
                matrix[n + out][out] = c;
            }
        }
    }
}
