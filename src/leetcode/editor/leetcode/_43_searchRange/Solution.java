package leetcode.editor._43_searchRange;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        // 查找左边界和右边界

        // 左边界
        int left = 0;
        int right = nums.length - 1;
        int mid;

        while (left < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        result[0] = left == right && target == nums[left] ? left : -1;


        // 右边界
        left = 0;
        right = nums.length - 1;

        while (left < right) {
            mid = (left + right) % 2 == 0 ? left + (right - left) / 2 : left + (right - left) / 2 + 1;
            if (nums[mid] == target) {
                left = mid;

            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        result[1] = left == right && target == nums[left] ? left : -1;

        return result;
    }
}