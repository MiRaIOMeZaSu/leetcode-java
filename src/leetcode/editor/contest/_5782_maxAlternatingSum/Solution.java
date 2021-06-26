package leetcode.editor.contest._5782_maxAlternatingSum;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        // 类似股票问题的动态规划
        int size = nums.length;
        long[] base = new long[2];
        base[0] = nums[0];
        base[1] = Integer.MIN_VALUE;
        for (int i = 1; i < size; i++) {
            long[] next = new long[2];
            next[0] = Math.max(0, base[1]) + nums[i];
            next[1] = base[0] - nums[i];
            base[0] = Math.max(base[0], next[0]);
            base[1] = Math.max(base[1], next[1]);
        }
        return Math.max(base[0], base[1]);
    }
}