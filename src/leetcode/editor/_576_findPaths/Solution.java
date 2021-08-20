package leetcode.editor._576_findPaths;

class Solution {
    int pivot = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        // 动态规划
        if (maxMove == 0) {
            return 0;
        }
        int[][][] dp = new int[m][n][maxMove + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                dp[i * (m - 1)][j][1]++;
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < m; j++) {
                dp[j][i * (n - 1)][1]++;
            }
        }
        for (int x = 2; x <= maxMove; x++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int[] temp = new int[4];
                    temp[0] = i - 1 < 0 ? 0 : dp[i - 1][j][x - 1];
                    temp[1] = i + 1 >= m ? 0 : dp[i + 1][j][x - 1];
                    temp[2] = j - 1 < 0 ? 0 : dp[i][j - 1][x - 1];
                    temp[3] = j + 1 >= n ? 0 : dp[i][j + 1][x - 1];
                    int total = 0;
                    for (int y = 0; y < 4; y++) {
                        total += temp[y];
                        total %= pivot;
                    }
                    dp[i][j][x] = total;
                }
            }
        }
        int result = 0;
        for (int i = 1; i <= maxMove; i++) {
            result += dp[startRow][startColumn][i];
            result %= pivot;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.findPaths(10,
                10,
                0,
                5,
                5);
        System.out.println(ret);
    }
}