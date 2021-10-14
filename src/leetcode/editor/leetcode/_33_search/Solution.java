package leetcode.editor.leetcode._33_search;

class Solution {
    public int search(int[] nums, int target) {
        // 在低谷中寻找数字(二分法)
        int size = nums.length;
        int left = 0;
        int right = size - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] < target) {
                if (nums[mid] < nums[mid + 1]) {
                    int temp = solve(nums, mid + 1, right, target);
                    if (temp != -1) {
                        return temp;
                    }
                    right = mid - 1;
                } else {
                    int temp = solve(nums, left, mid - 1, target);
                    if (temp != -1) {
                        return temp;
                    }
                    left = mid + 1;
                }
            } else if (nums[mid] > target) {
                if (nums[mid] < nums[mid + 1]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                return mid;
            }
        }
        return -1;
    }

    private int solve(int[] nums, int left, int right, int target) {
        boolean flag = nums[left] > nums[right];
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                if (flag) {
                    // 递减
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (flag) {
                    // 递减
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
    }
}