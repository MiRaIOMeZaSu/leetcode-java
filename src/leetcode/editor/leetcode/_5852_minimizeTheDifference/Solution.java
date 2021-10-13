package leetcode.editor._5852_minimizeTheDifference;

class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        // 暴力法: 70^70
        // 800 * 70 * 70
        // 背包动态规划法!?
        int m = mat.length;
        int n = mat[0].length;
        boolean[] curr = new boolean[10000];
        int max = mat[0][0];
        for (int i = 0; i < n; i++) {
            curr[mat[0][i]] = true;
            max = Math.max(mat[0][i], max);
        }
        for (int i = 1; i < m; i++) {
            boolean[] next = new boolean[10000];
            int nextMax = max + mat[i][0];
            for (int x = 1; x <= max; x++) {
                if (curr[x]) {
                    for (int j = 0; j < n; j++) {
                        next[x + mat[i][j]] = true;
                        nextMax = Math.max(nextMax, x + mat[i][j]);
                    }
                }
            }
            max = nextMax;
            curr = next;
        }
        int i = 0;
        while (true) {
            if (target - i >= 0 && curr[target - i]) {
                return i;
            }
            if (target + i <= 10000 && curr[target + i]) {
                return i;
            }
            i++;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minimizeTheDifference(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 13);
    }
}
