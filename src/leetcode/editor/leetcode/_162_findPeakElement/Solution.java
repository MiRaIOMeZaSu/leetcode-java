package leetcode.editor._162_findPeakElement;

class Solution {
    public int findPeakElement(int[] nums) {
        // 使用二分法
        // 每次都望一个方向找，必然会找到一个峰值
        int size = nums.length;
        if (size == 1) {
            return 0;
        }
        if (nums[1] < nums[0]) {
            return 0;
        }
        if (nums[size - 2] < nums[size - 1]) {
            return size - 1;
        }
        int left = 0;
        int right = size - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (mid - 1 >= 0 && nums[mid - 1] > nums[mid]) {
                // 向左爬坡
                right = mid - 1;
            } else if (mid + 1 < size && nums[mid + 1] > nums[mid]) {
                // 向右爬坡
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findPeakElement(new int[]{3, 4, 3, 2, 1});
    }
}