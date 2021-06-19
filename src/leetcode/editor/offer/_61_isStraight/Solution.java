package leetcode.editor.offer._61_isStraight;

import java.util.Arrays;

class Solution {
    public boolean isStraight(int[] nums) {
        int zeroNum = 0;
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length && nums[i] == 0) {
            zeroNum++;
            i++;
        }
        if (nums[4] - nums[i] - zeroNum > 5) {
            return false;
        }
        for (int j = i + 1; j < 5; j++) {
            if (nums[j] != nums[j - 1] + 1) {
                zeroNum -= nums[j] - (nums[j - 1] + 1);
            }
            if(nums[j]==nums[j - 1]){
                return false;
            }
            if (zeroNum < 0) {
                return false;
            }
        }
        return true;
    }
}