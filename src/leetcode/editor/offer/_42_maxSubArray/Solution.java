package leetcode.editor.offer._42_maxSubArray;

class Solution {
    public int maxSubArray(int[] nums) {
        int size = nums.length;
        int min = 0;
        int result = Integer.MIN_VALUE;
        int prefix = 0;
        int biggest = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            prefix = nums[i] + prefix;
            min = Math.min(min, prefix);
            result = Math.max(result, prefix - min);
            biggest = Math.max(biggest, nums[i]);
        }
        return biggest < 0 ? biggest : result;
    }
}