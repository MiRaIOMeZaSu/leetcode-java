package leetcode.editor._645_findErrorNums;

class Solution {
    public int[] findErrorNums(int[] nums) {
        int pivot = -1;
        int size = nums.length;
        int index = 0;
        while (index < size) {
            if (nums[index] != index + 1) {
                int temp = nums[nums[index] - 1];
                if (temp == nums[index]) {
                    pivot = temp;
                    index++;
                    continue;
                }
                nums[nums[index] - 1] = nums[index];
                nums[index] = temp;
                continue;
            }
            index++;
        }
        int missed = -1;
        for (int i = 0; i < size; i++) {
            if (nums[i] == pivot && i + 1 != pivot) {
                missed = i + 1;
                break;
            }
        }
        return new int[]{pivot, missed};
    }
}