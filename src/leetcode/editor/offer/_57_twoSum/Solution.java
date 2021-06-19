package leetcode.editor.offer._57_twoSum;

import java.util.HashSet;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 输入是递增的
        // 二分法?
        // 窗口法?
        // 更多前置基本判断
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int i = 0;
        while (i < nums.length && nums[i] < target) {
            if (set.contains(target - nums[i])) {
                return new int[]{nums[i], target - nums[i]};
            }
            i++;
        }
        return new int[]{};
    }
}