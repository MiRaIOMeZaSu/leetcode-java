package leetcode.editor._673_findNumberOfLIS;

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int size = nums.length;
        int[] leftMax = new int[size];
        int[] dp = new int[size];
        dp[0] = 1;
        leftMax[0] = nums[0];
        for (int i = 1; i < size; i++) {
            leftMax[i] = Math.max(nums[i], leftMax[i - 1]);
            int j = i - 1;
            do {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (leftMax[j] > nums[j]) {
                        j--;
                    } else {
                        break;
                    }
                } else if (nums[i] == nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                    break;
                } else {
                    j--;
                }
            }
            while (j >= 0);
        }
        return dp[size - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findNumberOfLIS(new int[]{1, 3, 5, 4, 7});
    }
}