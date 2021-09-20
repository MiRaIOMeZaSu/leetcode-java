package leetcode.editor._673_findNumberOfLIS;

import java.util.regex.Pattern;

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int size = nums.length;
        int[] leftMax = new int[size];
        int[] dp = new int[size];
        int[] lenMax = new int[size];
        dp[0] = 1;
        lenMax[0] = 1;
        leftMax[0] = nums[0];
        int maxLen = 1;
        for (int i = 1; i < size; i++) {
            leftMax[i] = Math.max(nums[i], leftMax[i - 1]);
            int j = i - 1;
            maxLen = Math.max(maxLen, lenMax[i - 1]);
            do {
                if (nums[i] > nums[j]) {
                    if (lenMax[j] + 1 > lenMax[i]) {
                        lenMax[i] = lenMax[j] + 1;
                        dp[i] = 0;
                        dp[i] += dp[j];
                    } else if (lenMax[j] + 1 == lenMax[i]) {
                        dp[i] += dp[j];
                    }
                    if (leftMax[j] == nums[j]) {
                        break;
                    }
                } else if (nums[i] == nums[j]) {
                    if (lenMax[j] > lenMax[i]) {
                        lenMax[i] = lenMax[j];
                        dp[i] = dp[j];
                        break;
                    } else if (lenMax[j] == lenMax[i]) {
                        dp[i] += dp[j];
                    } else {
                        break;
                    }
                }
                j--;
            }
            while (j >= 0);
        }
        maxLen = Math.max(maxLen, lenMax[size - 1]);
        int ans = 0;
        for (int i = size - 1; i >= 0; i--) {
            if (maxLen == lenMax[i]) {
                ans += dp[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
    }
}