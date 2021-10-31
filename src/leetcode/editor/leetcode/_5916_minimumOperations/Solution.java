package leetcode.editor.leetcode._5916_minimumOperations;

import java.util.*;

class Solution {
    boolean[] visit = new boolean[1001];
    int target;
    int[] nums;
    int size;

    public int minimumOperations(int[] nums, int start, int goal) {
        // 使用广度优先算法,才能算出最小次数
        if (start == goal) {
            return 0;
        }
        this.nums = nums;
        this.target = goal;
        this.size = nums.length;
        Deque<int[]> integerDeque = new LinkedList<>();
        integerDeque.add(new int[]{0, start});
        visit[start] = true;
        while (!integerDeque.isEmpty()) {
            int[] pivot = integerDeque.poll();
            int[] next;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < 3; j++) {
                    next = new int[]{pivot[0] + 1, handle(j, pivot[1], nums[i])};
                    if (next[1] == goal) {
                        return next[0];
                    }
                    if (next[1] >= 0 && next[1] <= 1000 && !visit[next[1]]) {
                        integerDeque.add(next);
                        visit[next[1]] = true;
                    }
                }
            }
        }
        return -1;
    }

    private int handle(int id, int x, int num) {
        switch (id) {
            case 0:
                return x + num;
            case 1:
                return x - num;
            default:
                return x ^ num;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minimumOperations(new int[]{1, 3}, 6, 4);
    }
}