package leetcode.editor.contest._5955_maxTotalFruits;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        // 起始位置是固定的,计算往两边走的最佳情况和消耗
        // 使用前缀法
        int size = fruits.length;
        int positionBorder = fruits[size - 1][0];
        if (startPos > positionBorder) {
            k -= startPos - positionBorder;
            startPos = positionBorder;
        }
        int[] position = new int[positionBorder + 2];
        int last = 0;
        int currIndex = 0;
        int left = -1;
        int right = -1;
        int thisPosition = 0;
        int ans = 0;
        for (int i = 0; i <= positionBorder; i++) {
            if (i > fruits[currIndex][0]) {
                currIndex++;
            }
            int[] fruit = fruits[currIndex];
            if (fruit[0] < startPos) {
                left = currIndex;
            }
            if (right == -1 && fruit[0] > startPos) {
                right = currIndex;
            }
            if (fruit[0] == startPos) {
                thisPosition = fruit[1];
            }
            // 开始进行position的插入?
            if (fruit[0] == i) {
                last += fruit[1];
            }
            position[i + 1] = last;
        }
        if (k == 0) {
            return thisPosition;
        } else if (k < 0) {
            return 0;
        }
        // 此时前缀表构建完毕
        int minLeft = Math.max(0, startPos - k);
        ans = Math.max(ans, position[startPos + 1] - position[minLeft]);
        // 往前走
        if (right == -1) {
            return ans;
        }
        for (int i = right; i < size; i++) {
            // 先往右走
            int needle = fruits[i][0] - startPos;
            int rest = k - needle;
            if (rest >= 0) {
                int currLeft = Math.min(startPos, Math.max(fruits[i][0] - rest, 0));
                ans = Math.max(ans, position[fruits[i][0] + 1] - position[currLeft]);
            } else {
                // 说明无法到达此处
                break;
            }
            // 先往左走
            int currLeft = Math.max((startPos - rest / 2), 0);
            ans = Math.max(ans, position[fruits[i][0] + 1] - position[currLeft]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxTotalFruits(new int[][]{{5, 8}, {7, 7}, {8, 7}, {15, 5}, {18, 8}, {19, 3}, {25, 4}, {26, 1}, {34, 10}, {38, 3}, {39, 4}, {40, 5}}, 6,
                19));
    }
}