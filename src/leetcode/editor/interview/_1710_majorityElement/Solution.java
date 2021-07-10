package leetcode.editor.interview._1710_majorityElement;

import java.util.Arrays;

class Solution {
    public int majorityElement(int[] nums) {
        int size = nums.length;
        int index = size >> 1;
        Arrays.sort(nums);
        int pivot = nums[index];
        int count = 1;
        for (int i = index + 1; i < size; i++) {
            if (nums[i] != pivot) {
                break;
            }
            count++;
        }
        for (int i = index - 1; i >= 0; i--) {
            if (nums[i] != pivot) {
                break;
            }
            count++;
        }
        if (count <= index) {
            return -1;
        }
        return pivot;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.majorityElement(new int[]{2, 3});
    }
}