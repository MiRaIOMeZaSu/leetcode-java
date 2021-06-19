package leetcode.editor.offer._40_getLeastNumbers;

import java.util.Arrays;

class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr.length == 0 || k == 0) {
            return new int[]{};
        }
        Arrays.sort(arr);
        int size = arr.length;
        int[] ret = new int[k];
        ret[0] = arr[0];
        int j = 1;
        for (int i = 1; i < k; i++) {
            while (j < size) {
                if (arr[j] != arr[j - 1]) {
                    ret[i] = arr[j];
                    j++;
                    break;
                }
                j++;
            }
        }
        return ret;
    }
}