package leetcode.editor.offer._21_exchange;

class Solution {
    public int[] exchange(int[] nums) {
        // 奇数从0开始放,偶数从-1开始放
        int size = nums.length;
        int left = 0;
        int right = size - 1;
        while (left < right) {
            if (nums[left] % 2 == 0) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                right--;
            } else {
                left += 1;
            }
        }
        return nums;
    }
}