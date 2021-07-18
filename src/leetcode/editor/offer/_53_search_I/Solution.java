package leetcode.editor.offer._53_search_I;

class Solution {
    public int search(int[] nums, int target) {
        int result = 0;
        int size = nums.length;
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int num = nums[mid];
            if (num < target) {
                left = mid + 1;
            } else if (num > target) {
                right = mid - 1;
            } else {
                // 开始往两边寻找
                result += 1;
                for (int i = mid + 1; i < size; i++) {
                    if (nums[i] != target) {
                        break;
                    }
                    result++;
                }
                for (int i = mid - 1; i >= 0; i--) {
                    if (nums[i] != target) {
                        break;
                    }
                    result++;
                }
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.search(new int[]{5,7,7,8,8,10},10);
        System.out.println(result);
    }
}