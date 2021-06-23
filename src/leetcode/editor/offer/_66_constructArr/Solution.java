package leetcode.editor.offer._66_constructArr;

class Solution {
    public int[] constructArr(int[] a) {
        int size = a.length;
        if (size == 0) {
            return new int[]{};
        } else if (size == 1) {
            return new int[]{1};
        }
        int[] prefix = new int[size];
        int[] tail = new int[size];
        prefix[0] = a[0];
        tail[size - 1] = a[size - 1];
        for (int i = 1; i < size; i++) {
            prefix[i] = prefix[i - 1] * a[i];
            tail[size - 1 - i] = tail[size - i] * a[size - 1 - i];
        }
        int[] ret = new int[size];
        ret[0] = tail[1];
        ret[size - 1] = prefix[size - 2];
        for (int i = 1; i < size - 1; i++) {
            ret[i] = prefix[i - 1] * tail[i + 1];
        }
        return ret;
    }
}