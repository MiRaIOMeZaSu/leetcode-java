package leetcode.editor._5825_maxCompatibilitySum;

class Solution {
    int[][] students;
    int[][] mentors;
    int m, n;
    int[][] matrix;
    int result = Integer.MIN_VALUE;

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        this.students = students;
        this.mentors = mentors;
        m = students.length;
        n = students[0].length;
        matrix = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int x = 0; x < n; x++) {
                    matrix[i][j] += (students[i][x] ^ mentors[j][x] + 1) % 2;
                }
            }
        }
        // 使用回溯法?
        solve(new boolean[m], 0, 0);
        return result;
    }

    private void solve(boolean[] visit, int curr, int index) {
        if (index >= m) {
            result = Math.max(result, curr);
            return;
        }
        for (int i = 0; i < m; i++) {
            if (visit[i]) {
                continue;
            }
            visit[i] = true;
            solve(visit, curr + matrix[i][index], index + 1);
            visit[i] = false;
        }
    }
}