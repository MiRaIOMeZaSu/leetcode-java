package leetcode.editor.contest._5803_longestCommonSubpath;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestCommonSubpath(int n, int[][] paths) {
        // paths的length会非常大
        // 遍历并构造图
        // 寻找图的最长路径(最长路径如何寻找?)
        Set<Integer>[] sets = new HashSet[n];
        for (int i = 0; i < n; i++) {
            sets[i] = new HashSet<>();
        }
        int[] count = new int[n];
        int m = paths.length;
        for (int i = 0; i < m; i++) {
            sets[paths[i][0]].add(paths[i][1]);
            int nSize = paths[i].length;
            boolean[] visit = new boolean[nSize];
            int j = 0;
            visit[paths[i][j]] = true;
            count[paths[i][j]]++;
            j++;
            for (; j < nSize - 1; i++) {
                if (!visit[paths[i][j]]) {
                    visit[paths[i][j]] = true;
                    count[paths[i][j]]++;
                }
                sets[paths[i][j]].add(paths[i][j - 1]);
                sets[paths[i][j]].add(paths[i][j + 1]);
            }
            if (!visit[paths[i][j]]) {
                visit[paths[i][j]] = true;
                count[paths[i][j]]++;
            }
            sets[paths[i][j]].add(paths[i][j - 1]);
        }
        // 图建立完毕,开始寻找最长路径
        // 排序+动态规划?
        int legalPoint = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] == m) {
                legalPoint++;
            }
        }
        int res = 0;
        return res;
    }
}