package leetcode.editor._805_splitArraySameAverage;

class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        // 只要求均值相同,和并不需要相同
        // 求出加和,则可以通过当前加和获得剩余数的加和(此种方法无法获得数量)
        // 01背包问题
        // 存储并遍历数量?
        // 范围:0~300000
        // 直接使用哈希表代替数组?
        int size = nums.length;
        int minNum = getMin(nums);
        int total = 0;
        for (int i = 0; i < size; i++) {
            nums[i] -= minNum;
            total += nums[i];
        }
        boolean[] table = new boolean[total + 1];
        boolean[][] dp = new boolean[total + 1][size + 1];
        table[0] = true;
        dp[0][0] = true;
        int curr = 0;
        for (int i = 0; i < size; i++) {
            for (int j = curr; j >= 0; j--) {
                if (table[j]) {
                    for (int k = 0; k < size; k++) {
                        if (dp[j][k]) {
                            if (!dp[j + nums[i]][k + 1]) {
                                if (size - k - 1 != 0 && (size - k - 1) * (j + nums[i]) == (total - j - nums[i]) * (k + 1)) {
                                    return true;
                                }
                                dp[j + nums[i]][k + 1] = true;
                            }
                            table[j + nums[i]] = true;
                        }
                    }
                }
            }
            curr += nums[i];
        }
        return false;
    }

    private int getMin(int[] nums) {
        int size = nums.length;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (nums[i] < nums[index]) {
                index = i;
            }
        }
        return nums[index];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.splitArraySameAverage(new int[]{3, 1});
    }
}