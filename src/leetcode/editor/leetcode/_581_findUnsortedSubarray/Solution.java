package leetcode.editor._581_findUnsortedSubarray;

import java.lang.reflect.Array;
import java.util.Arrays;

class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int size = nums.length;
        int[] arr = Arrays.copyOf(nums, size);
        Arrays.sort(nums);
        int temp = 0;
        int i = 0;
        for (; i < size; i++) {
            if (arr[i] == nums[i]) {
                temp++;
            } else {
                break;
            }
        }

        for (int j = size - 1; j >= 0 && j > i; j--) {
            if (arr[j] == nums[j]) {
                temp++;
            } else {
                break;
            }
        }
        return size - temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15});
    }
}