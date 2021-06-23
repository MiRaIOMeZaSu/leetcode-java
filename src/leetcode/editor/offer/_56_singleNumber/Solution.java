package leetcode.editor.offer._56_singleNumber;

import java.util.Arrays;

class Solution {
    public int singleNumber(int[] nums) {
        int size = nums.length;
        if (size == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        if (nums[size - 1] != nums[size - 2]) {
            return nums[size - 1];
        }
        int ret = 0;
        for (int i = 0; i < size; i += 3) {
            if (nums[i] != nums[i + 1]) {
                ret = nums[i];
                break;
            }
        }
        return ret;
    }
}