package leetcode.editor.contest._5908_countHighestScoreNodes;


class Solution {
    int[] count;
    int[][] tree;
    int[] parents;
    Long max = Long.MIN_VALUE;
    int ans = 0;
    int total;

    public int countHighestScoreNodes(int[] parents) {
        // 先构造树,再遍历求值
        int size = parents.length;
        tree = new int[size][2];
        count = new int[size];
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < 2; j++) {
                if (tree[parents[i]][j] == 0) {
                    tree[parents[i]][j] = i;
                    break;
                }
            }
        }
        total = solve(tree[0][0]) + solve(tree[0][1]) + 1;
        ans = 1;
        max = 1L;
        for (int i = 0; i < 2; i++) {
            if (tree[0][i] != 0) {
                max *= count[tree[0][i]];
            }
        }
        for (int i = 0; i < 2; i++) {
            if (tree[0][i] != 0) {
                process(tree[0][i]);
            }
        }
        System.out.println(ans);
        return ans;
    }

    private void process(int rootIndex) {
        long temp = total - count[rootIndex];
        for (int i = 0; i < 2; i++) {
            if (tree[rootIndex][i] != 0) {
                process(tree[rootIndex][i]);
                temp *= count[tree[rootIndex][i]];
            }
        }
        if (temp > max) {
            max = temp;
            ans = 1;
        } else if (temp == max) {
            ans++;
        }
    }

    private int solve(int rootIndex) {
        if (rootIndex == 0) {
            return 0;
        }
        count[rootIndex] = solve(tree[rootIndex][0]) + solve(tree[rootIndex][1]) + 1;
        return count[rootIndex];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.countHighestScoreNodes(new int[]{-1, 2, 0, 2, 0});
    }
}

