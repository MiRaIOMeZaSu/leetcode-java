package leetcode.editor.offer._56_singleNumbers;

import java.util.Arrays;

class Solution {
    public int[] singleNumbers(int[] nums) {
        int bit = 0;
        for (int i = 0; i < nums.length; i++) {
            bit ^= nums[i];
        }
        Arrays.sort(nums);
        int[] ret = new int[2];
        for (int i = 0; i < nums.length; i += 2) {
            if (nums[i] != nums[i + 1]) {
                ret[0] = nums[i];
                ret[1] = nums[i] ^ bit;
                break;
            }
        }
        return ret;
    }
}