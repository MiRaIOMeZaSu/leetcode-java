package leetcode.editor.LCCUP._20220416_._2_perfectMenu;

class Solution {
    int ans = -1;
    int limit;
    int[][] attribute;
    int[][] cookbooks;

    public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        // 只有5中食材
        // 最多5个菜,每个菜最多一次
        // 直接遍历选择
        this.cookbooks = cookbooks;
        this.attribute = attribute;
        this.limit = limit;
        solve(materials, 0, new int[]{0, 0});
        return ans;
    }

    private void solve(int[] curr, int cookbooksIndex, int[] state) {
        if (cookbooksIndex >= cookbooks.length) {
            // 开始总结
            if (state[1] >= limit) {
                ans = Math.max(ans, state[0]);
            }
            return;
        }
        // 用
        for (int i = 0; i < curr.length; i++) {
            curr[i] -= cookbooks[cookbooksIndex][i];
        }
        boolean flag = true;
        for (int i = 0; i < curr.length; i++) {
            if (curr[i] < 0) {
                flag = false;
                break;
            }
        }
        state[0] += attribute[cookbooksIndex][0];
        state[1] += attribute[cookbooksIndex][1];
        if (flag) {
            // 开始继续
            solve(curr, cookbooksIndex + 1, state);
        }
        state[0] -= attribute[cookbooksIndex][0];
        state[1] -= attribute[cookbooksIndex][1];
        for (int i = 0; i < curr.length; i++) {
            curr[i] += cookbooks[cookbooksIndex][i];
        }
        // 不用
        solve(curr, cookbooksIndex + 1, state);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.perfectMenu(
                new int[]{3, 2, 4, 1, 2},
                new int[][]{{1, 1, 0, 1, 2}, {2, 1, 4, 0, 0}, {3, 2, 4, 1, 0}},
                new int[][]{{3, 2}, {2, 4}, {7, 6}}, 5
        );
    }
}