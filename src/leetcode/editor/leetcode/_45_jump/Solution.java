package leetcode.editor._45_jump;

class Solution {
    public int jump(int[] nums) {
        int size = nums.length;
        int[] dp = new int[size];
        for (int i = 0; i < size; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for (int i = 0; i < size; i++) {
            for (int j = Math.min(size - 1, i + nums[i]); j > i && dp[i] + 1 < dp[j]; j--) {
                dp[j] = dp[i] + 1;
            }
        }
        return dp[size - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.jump(new int[]{2, 3, 1, 1, 4}));
    }
}