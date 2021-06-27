package leetcode.editor.contest._5798_rotateGrid;

import java.util.HashSet;

class Solution {
    int[][] graph;
    int total;

    public int[][] rotateGrid(int[][] grid, int k) {
        // 分析法,计算位置
        graph = grid;
        int m = grid.length;
        int n = grid[0].length;
        int times = Math.min(m, n) >> 1;
        for (int i = 0; i < times; i++) {
            solve(m - i * 2, n - i * 2, i, k);
        }
        return graph;
    }

    private void solve(int m, int n, int start, int k) {
        total = m * 2 + n * 2 - 4;
        k = k % total;

        HashSet<Integer> visit = new HashSet<>();
        int[] curr = new int[]{start, start};
        int[] target = getIndex(start, curr[0], curr[1], m, n, k);
        do {
            total = _solve(curr, true, start, graph[curr[0]][curr[1]], target[0], target[1], m, n, k, 0, visit);
            curr = getIndex(start, curr[0], curr[1], m, n, 1);
            target = getIndex(start, curr[0], curr[1], m, n, k);
        } while (total != 0);
    }

    private int _solve(int[] curr, boolean flag, int start, int num, int i, int j, int m, int n, int k, int count, HashSet<Integer> visit) {
        if (count == total) {
            return 0;
        }
        int temp = graph[i][j];
        if (visit.contains(temp)) {
            return total - count;
        }
        visit.add(num);
        graph[i][j] = num;
        if (flag) {
            graph[curr[0]][curr[1]] = -1;
        }
        if (temp == -1) {
            return total - count - 1;
        }
        int[] target = getIndex(start, i, j, m, n, k);
        return _solve(new int[]{i, j}, false, start, temp, target[0], target[1], m, n, k, count + 1, visit);
    }

    private int[] getIndex(int start, int i, int j, int m, int n, int k) {
        while (k != 0) {
            if (i == start) {
                int handle = Math.min(k, j - start);
                j -= handle;
                k -= handle;
            }
            if (j == start) {
                int handle = Math.min(k, (start + m - 1) - i);
                i += handle;
                k -= handle;
            }
            if (i == start + m - 1) {
                int handle = Math.min(k, (start + n - 1) - j);
                j += handle;
                k -= handle;
            }
            if (j == start + n - 1) {
                int handle = Math.min(k, i - start);
                i -= handle;
                k -= handle;
            }
        }
        return new int[]{i, j};
    }
}