package leetcode.editor._789_escapeGhosts;

class Solution {
    int[][] ghosts;
    int[][] graph;
    int[][] visit;
    int[][] items = new int[][]{
            {0, -1}, {0, 1}, {1, 0}, {-1, 0}
    };
    int m, n;
    int[] target;

    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        this.target = target;
        this.ghosts = ghosts;
        m = target[1];
        n = target[0];
        int toPi = Math.min(m, 0);
        int toPj = Math.min(n, 0);
        for (int i = 0; i < ghosts.length; i++) {
            toPi = Math.min(toPi, ghosts[i][0]);
            toPj = Math.min(toPj, ghosts[i][1]);
            m = Math.max(m + 1, ghosts[i][0] + 1);
            n = Math.max(n + 1, ghosts[i][1] + 1);
        }
        for (int i = 0; i < ghosts.length; i++) {
            ghosts[i][0] -= toPi;
            ghosts[i][1] -= toPj;
        }
        m += -toPi + 1;
        n += -toPj + 1;
        graph = new int[m][n];
        visit = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.MAX_VALUE;
                visit[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < ghosts.length; i++) {
            draw(ghosts[i][0], ghosts[i][1], 0);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        target[0] -= toPi;
        target[1] -= toPj;
        return solve(-toPi, -toPj, 0);
    }

    private void draw(int i, int j, int val) {
        if (graph[i][j] <= val) {
            return;
        }
        graph[i][j] = val;
        for (int s = 0; s < 4; s++) {
            int y = i + items[s][0];
            int x = j + items[s][1];
            if (y >= 0 && x >= 0 && y < m && x < n) {
                draw(y, x, val + 1);
            }
        }
    }

    private boolean solve(int i, int j, int val) {
        if (!(i >= 0 && j >= 0 && i < m && j < n)) {
            return false;
        }
        if (val >= graph[i][j]) {
            return false;
        }
        if (visit[i][j] <= val) {
            return false;
        }
        visit[i][j] = val;
        if (i == target[0] && j == target[1]) {
            return true;
        }
        for (int s = 0; s < 4; s++) {
            int y = i + items[s][0];
            int x = j + items[s][1];
            if (y >= 0 && x >= 0 && y < m && x < n) {
                if (solve(y, x, val + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.escapeGhosts(new int[][]{{-1, 2}, {0, 1}, {-2, 3}, {0, 1}, {-5, 0}}, new int[]{-2, 0}));
    }
}