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
        for (int i = 0; i < ghosts.length; i++) {
            m = Math.max(m + 1, ghosts[i][0] + 1);
            n = Math.max(n + 1, ghosts[i][1] + 1);
        }
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
        return solve(0, 0, 0);
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
        if (val > graph[i][j]) {
            return false;
        }
        if (visit[i][j] <= val) {
            return false;
        }
        visit[i][j] = val;
        if (i == target[0] && j == target[1]) {
            return true;
        }
        int a1 = i + 1;
        int b1 = j;
        int a2 = i;
        int b2 = j + 1;
        if (Math.pow(a1 - target[0], 2) + Math.pow(b1 - target[1], 2) <=
                Math.pow(a2 - target[0], 2) + Math.pow(b2 - target[1], 2)) {
            return solve(a1, b1, val + 1) || solve(a2, b2, val + 1);
        } else {
            return solve(a2, b2, val + 1) || solve(a1, b1, val + 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.escapeGhosts(new int[][]{{1, 0}, {0, 3}}, new int[]{0, 1}));
    }
}