package leetcode.editor._238_productExceptSelf;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] ret = new int[nums.length];
        ret[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            ret[i] = ret[i + 1] * nums[i + 1];
        }
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ret[i] *= temp;
            temp *= nums[i];
        }
        return ret;
    }
}