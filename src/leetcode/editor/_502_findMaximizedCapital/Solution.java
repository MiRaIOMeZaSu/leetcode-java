package leetcode.editor._502_findMaximizedCapital;

import java.lang.reflect.Array;
import java.util.*;

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // 资产为多少时选择了多少个项目时将获得多少利润
        // 进行排序
        // 背包法?
        // 直接插入排序
        int size = profits.length;
        int[][] table = new int[size][2];
        for (int i = 0; i < size; i++) {
            table[i][0] = capital[i];
            table[i][1] = profits[i];
        }
        Arrays.sort(table, (o1, o2) -> {
            return o1[0] - o2[0];
//            int diff = o1[0] - o2[0];
//            if (diff != 0) {
//                return diff;
//            }
//            return o2[1] - o1[1];
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int currK = 0;
        int result = w;
        int currIndex = 0;
        while (currK < k) {
            while (currIndex < size && table[currIndex][0] <= result) {
                queue.add(table[currIndex][1]);
                currIndex++;
            }
            if (queue.isEmpty()) {
                return result;
            }
            result += queue.poll();
            currK += 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1});
    }
}