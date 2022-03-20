package leetcode.editor.contest._6029_maximumBobPoints;

import java.util.List;

class Solution {
    private final int[] ans = new int[12];
    private int[] aliceArrows;
    private int maxPoint = -1;

    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        // 背包问题动态规划
        // 2^11
        this.aliceArrows = aliceArrows;
        solve(0, numArrows, new int[12], 11);
        return ans;
    }

    private void solve(int currPoint, int restArrow, int[] choice, int index) {
        if (restArrow == 0 || index == 0) {
            if (currPoint > maxPoint) {
                maxPoint = currPoint;
                System.arraycopy(choice, 0, ans, 0, choice.length);
                if (restArrow > 0) {
                    ans[0] = restArrow;
                }
            }
            return;
        }
        int ifWinUse = aliceArrows[index] + 1;
        int isWinRest = restArrow - ifWinUse;
        if (isWinRest >= 0) {
            choice[index] = ifWinUse;
            solve(currPoint + index, isWinRest, choice, index - 1);
            choice[index] = 0;
        }
        solve(currPoint, restArrow, choice, index - 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumBobPoints(89, new int[]{3, 2, 28, 1, 7, 1, 16, 7, 3, 13, 3, 5}));
//        System.out.println(solution.maximumBobPoints(8, new int[]{0, 0, 3, 0, 0, 0, 0, 0, 0, 2, 3, 0}));
//        System.out.println(solution.maximumBobPoints(3, new int[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2}));
//        System.out.println(solution.maximumBobPoints(9, new int[]{1, 1, 0, 1, 0, 0, 2, 1, 0, 1, 2, 0}));
    }
}