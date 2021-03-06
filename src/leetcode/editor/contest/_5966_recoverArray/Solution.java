package leetcode.editor.contest._5966_recoverArray;

import java.util.*;

class Solution {
    public int[] recoverArray(int[] nums) {
        // 重点是k的值
        // 使得数字排列满足条件
        // k的范围是固定的
        int size = nums.length;
        int k = -1;
        int[] tempNumCountKey = new int[size];
        Map<Integer, Integer> counts = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            if (counts.merge(nums[i], 1, Integer::sum) == 1) {
                tempNumCountKey[counts.size() - 1] = nums[i];
            }
        }
        int distinctSize = counts.size();
        int[] numCountVal = new int[distinctSize];
        int[] numCountKey = new int[distinctSize];
        System.arraycopy(tempNumCountKey, 0, numCountKey, 0, distinctSize);
        Arrays.sort(numCountKey);
        for (int i = 0; i < distinctSize; i++) {
            numCountVal[i] = counts.get(numCountKey[i]);
        }
        for (int i = 0; i < distinctSize; i++) {
            counts.put(numCountKey[i], i);
        }
        int[] ans = new int[size / 2];
        for (int i = 1; i < distinctSize; i++) {
            int currK = Math.abs(numCountKey[i] - numCountKey[0]);
            if (currK % 2 == 0) {
                currK >>= 1;
                // 继续判断
                int[] copyCountVal = new int[distinctSize];
                System.arraycopy(numCountVal, 0, copyCountVal, 0, distinctSize);
                if (copyCountVal[i] < copyCountVal[0]) {
                    continue;
                }
                copyCountVal[i] -= copyCountVal[0];
                int pivot = copyCountVal[0] * 2;
                for (int j = 1; j < distinctSize && pivot < size; j++) {
                    if (copyCountVal[j] != 0) {
                        Integer targetIndex = counts.get(numCountKey[j] + currK * 2);
                        if (targetIndex == null) {
                            break;
                        }
                        if (copyCountVal[targetIndex] < copyCountVal[j]) {
                            break;
                        }
                        copyCountVal[targetIndex] -= copyCountVal[j];
                        pivot += copyCountVal[j] * 2;
                    }
                }
                if (pivot == size) {
                    k = currK;
                    break;
                }
            }
        }
        if (k > 0) {
            // 开始计算
            int index = 0;
            int ansSize = size / 2;
            int[] copyCountVal = new int[distinctSize];
            System.arraycopy(numCountVal, 0, copyCountVal, 0, distinctSize);
            for (int j = 0; j < distinctSize && index < ansSize; j++) {
                if (copyCountVal[j] != 0) {
                    Integer targetIndex = counts.get(numCountKey[j] + k * 2);
                    copyCountVal[targetIndex] -= copyCountVal[j];
                    for (int i = 0; i < copyCountVal[j]; i++) {
                        ans[index] = numCountKey[j] + k;
                        index++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recoverArray(new int[]{36, 67, 13, 17, 14, 63, 69, 21, 45, 79, 8, 72, 64, 48, 23, 34, 58, 58, 76, 17, 14, 42, 58, 76, 94, 6, 46, 10, 98, 65, 39, 55, 88, 46, 32, 18, 18, 65, 79, 35, 59, 2, 14, 61, 4, 54, 68, 48, 41, 50, 26, 100, 80, 30, 92, 52, 30, 19, 2, 22, 96, 75, 100, 62, 83, 96, 62, 44, 45, 26, 41, 6});
    }
}