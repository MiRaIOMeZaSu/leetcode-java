package leetcode.editor.contest._5940_minimumDeletions;

class Solution {
    public int minimumDeletions(int[] nums) {
        // 直接计算?
        int size = nums.length;
        int ans = size;
        int min = nums[0];
        int max = nums[0];
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
            if (nums[i] < min) {
                min = nums[i];
                minIndex = i;
            }
        }
        // 开始比较,最小值和最大值都只有一个
        // 只从前往后
        int frontIndex = Math.min(maxIndex, minIndex);
        int backIndex = Math.max(maxIndex, minIndex);
        ans = Math.min(backIndex + 1, ans);
        // 只从后往前
        ans = Math.min(size - frontIndex, ans);
        // 只去两边
        ans = Math.min(frontIndex + 1 + (size - backIndex), ans);
        return ans;
    }
}