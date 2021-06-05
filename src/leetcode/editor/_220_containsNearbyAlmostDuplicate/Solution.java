package leetcode.editor._220_containsNearbyAlmostDuplicate;


class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 维护小顶堆(以绝对值做比较)
        // 只变换k
        int size = nums.length;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j + i < size; j++) {
                if (Math.abs(nums[j] - nums[j + i]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }
}