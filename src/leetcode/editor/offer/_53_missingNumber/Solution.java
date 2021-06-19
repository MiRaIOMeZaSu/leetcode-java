package leetcode.editor.offer._53_missingNumber;

class Solution {
    public int missingNumber(int[] nums) {
        // 递增
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }
}