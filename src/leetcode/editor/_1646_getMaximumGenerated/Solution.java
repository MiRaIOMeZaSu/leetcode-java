package leetcode.editor._1646_getMaximumGenerated;

class Solution {
    public int getMaximumGenerated(int n) {
        // 根据奇偶性质划分
        int[] nums = new int[n + 1];
        if (n == 0) {
            return 0;
        }
        nums[0] = 0;
        nums[1] = 1;
        int result = 1;
        for (int i = 2; i <= n; i++) {
            int temp = i >> 1;
            if (i % 2 == 0) {
                nums[i] = nums[temp];
            } else {
                nums[i] = nums[temp] + nums[temp + 1];
            }
            result = Math.max(result, nums[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.getMaximumGenerated(0);
    }
}