package leetcode.editor.leetcode._789_escapeGhosts;

class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int pivot = Math.abs(target[0]) + Math.abs(target[1]);
        for (int i = 0; i < ghosts.length; i++) {
            int distance = Math.abs(ghosts[i][0] - target[0]) + Math.abs(ghosts[i][1] - target[1]);
            if (distance <= pivot) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.escapeGhosts(new int[][]{{-1, 2}, {0, 1}, {-2, 3}, {0, 1}, {-5, 0}}, new int[]{-2, 0}));
    }
}