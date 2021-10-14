package leetcode.editor.leetcode._33_search;

class Solution {
    public int search(int[] nums, int target) {
        // 在低谷中寻找数字(二分法)
        int size = nums.length;
        // 对于数组中的数且left!=right,必然有nums[left] > nums[right]
        int left = 0;
        int right = size - 1;
        if (target == nums[left]) {
            return left;
        }
        if (target == nums[right]) {
            return right;
        }
        boolean flag = target >= nums[left];
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] < target) {
                if (nums[mid] < nums[0]) {
                    if (flag) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    if (flag) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            } else if (nums[mid] > target) {
                if (nums[mid] < nums[0]) {
                    right = mid - 1;
                } else {
                    if (flag) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            } else {
                return mid;
            }
        }
        return nums[left] == target ? left : -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 8));
    }
}