package leetcode.editor._31_nextPermutation;

import java.util.Arrays;

public class Solution {
    public void nextPermutation(int[] nums) {
        // 从后往前找,直到左值小于右边最大值
        int length = nums.length;
        if (length == 1) {
            return;
        }
        int maxIndex = length - 1;
        int i = length - 2;
        for (; i >= 0; i--) {
            if (nums[i] < nums[maxIndex]) {
                // 此时进行交换
                // 此时往后走直到获取到最小比nums[i]大的下标
                while (maxIndex + 1 < length&& nums[i] < nums[maxIndex + 1]) {
                    maxIndex++;
                }
                int temp = nums[i];
                nums[i] = nums[maxIndex];
                nums[maxIndex] = temp;
                break;
            } else if (nums[i] >= nums[maxIndex]) {
                // 此时更新maxIndex
                maxIndex = i;
            }
        }
        if (maxIndex == 0) {
            // 将数组倒过来(两头替换
            for (int j = 0; j < length / 2; j++) {
                int temp = nums[j];
                nums[j] = nums[length - 1 - j];
                nums[length - 1 - j] = temp;
            }
            return;
        }
        // 此时进行排序
        Arrays.sort(nums, i + 1, length);
    }
}
