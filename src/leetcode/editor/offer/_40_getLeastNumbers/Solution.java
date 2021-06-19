package leetcode.editor.offer._40_getLeastNumbers;

import java.util.Arrays;

class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr.length == 0 || k == 0) {
            return new int[]{};
        }
        Arrays.sort(arr);
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = arr[i];
        }
        return ret;
    }
}