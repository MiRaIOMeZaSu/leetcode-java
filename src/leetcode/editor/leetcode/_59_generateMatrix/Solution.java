package leetcode.editor.leetcode._59_generateMatrix;

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int curr = 1;
        int target = n * n;
        int i = 0;
        int j = 0;
        while (curr <= target) {
            // 向右横向
            while (j < n && ans[i][j] == 0) {
                ans[i][j] = curr;
                curr++;
                j++;
            }
            j--;
            i++;
            // 向下竖向
            while (i < n && ans[i][j] == 0) {
                ans[i][j] = curr;
                curr++;
                i++;
            }
            i--;
            j--;
            // 向左横向
            while (j >= 0 && ans[i][j] == 0) {
                ans[i][j] = curr;
                curr++;
                j--;
            }
            j++;
            i--;
            // 向上竖向
            while (i >= 0 && ans[i][j] == 0) {
                ans[i][j] = curr;
                curr++;
                i--;
            }
            j++;
            i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.generateMatrix(3);
    }
}