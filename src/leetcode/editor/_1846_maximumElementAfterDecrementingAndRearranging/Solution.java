package leetcode.editor._1846_maximumElementAfterDecrementingAndRearranging;

import java.util.Arrays;

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int size = arr.length;
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < size; i++) {
            if (arr[i] - arr[i - 1] > 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        return arr[size - 1];
    }
}