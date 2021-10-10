package leetcode.editor.leetcode_cup_multi._6;

class Solution {
    int size;

    public int safe(int a) {
        if (a >= size) {
            a -= size;
        }
        if (a < 0) {
            a += size;
        }
        return a;
    }

    public long ringGame(long[] challenge) {
        size = challenge.length;
        // 0位存所有或值,1位存最小要求
        // 再存区间内的最大值?
        long[] table = new long[60];
        table[0] = 1;
        for (int i = 1; i < 60; i++) {
            table[i] = table[i - 1] << 1;
        }
        long[][][] dp = new long[size][size][4];
        for (int i = 0; i < size; i++) {
            dp[i][i][0] = challenge[i];
            dp[i][i][1] = challenge[i];
            dp[i][i][2] = challenge[i];
            dp[i][i][3] = challenge[i];
        }
        for (int i = 2; i <= size; i++) {
            for (int j = 0; j < size; j++) {
                int left = j;
                int right = safe(j + i - 1);
                if (left == 2 && right == 3) {
                    System.out.println();
                }
                dp[left][right][1] = Long.MAX_VALUE;
                dp[left][right][3] = Math.max(dp[left][safe(right - 1)][3], dp[right][right][3]);
                dp[left][right][0] = dp[left][left][0] | dp[safe(left + 1)][right][0];
                // 往左走
                long rightPoint = dp[safe(left + 1)][right][0] | dp[safe(left + 1)][right][1];
                if (challenge[left] <= rightPoint) {
                    if (dp[left][right][1] > dp[safe(left + 1)][right][1]) {
                        dp[left][right][1] = dp[safe(left + 1)][right][1];
                        dp[left][right][2] = dp[safe(left + 1)][right][2];
                    }
                } else {
                    // 要求的分数较大时
                    // 目标有的最大位,当前值必须有
                    int pool = 0;
                    for (int k = 59; k >= 0; k--) {
                        if ((challenge[left] | table[k]) == challenge[left]) {
                            if ((dp[safe(left + 1)][right][1] | table[k]) != dp[safe(left + 1)][right][1]) {
                                pool |= table[k];
                            }
                        } else if ((dp[safe(left + 1)][right][1] | table[k]) == dp[safe(left + 1)][right][1]) {
                            break;
                        }
                    }
                    if (pool >= dp[safe(left + 1)][right][3]) {
                        dp[left][right][1] = pool;
                        dp[left][right][2] = pool;
                        continue;
                    }
                    if ((dp[safe(left + 1)][right][2] | pool) < dp[left][right][1]) {
                        dp[left][right][1] = pool | dp[left][safe(right - 1)][2];
                        dp[left][right][2] = dp[left][right][1];
                    }
                }
                // 往右走
                long leftPoint = dp[left][safe(right - 1)][1] | dp[left][safe(right - 1)][0];
                if (challenge[right] <= leftPoint) {
                    if (dp[left][right][1] > dp[left][safe(right - 1)][1]) {
                        dp[left][right][1] = dp[left][safe(right - 1)][1];
                        dp[left][right][2] = dp[left][safe(right - 1)][2];
                    }
                } else {
                    // 要求的分数较大时
                    // 目标有的最大位,当前值必须有
                    int pool = 0;
                    for (int k = 59; k >= 0; k--) {
                        if ((challenge[right] | table[k]) == challenge[right]) {
                            if ((dp[left][safe(right - 1)][0] | table[k]) != dp[left][safe(right - 1)][0]) {
                                pool |= table[k];
                            }
                        } else if ((dp[left][safe(right - 1)][0] | table[k]) == dp[left][safe(right - 1)][0]) {
                            break;
                        }
                    }
                    if (pool >= dp[left][safe(right - 1)][3]) {
                        dp[left][right][1] = pool;
                        dp[left][right][2] = pool;
                        continue;
                    }
                    if ((pool | dp[left][safe(right - 1)][2]) < dp[left][right][1]) {
                        dp[left][right][1] = pool | dp[left][safe(right - 1)][2];
                        dp[left][right][2] = dp[left][right][1];
                    }
                }
            }
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            ans = Math.min(ans, dp[i][safe(i + size - 1)][1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        long ans = solution.ringGame(new long[]{4,6,4});
        System.out.println(ans);
    }
}