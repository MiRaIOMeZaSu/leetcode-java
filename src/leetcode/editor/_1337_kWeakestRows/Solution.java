package leetcode.editor._1337_kWeakestRows;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    private final int pivot = Integer.MAX_VALUE - 1;

    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        for (int i = 0; i < m; i++) {
            int temp = sum(mat[i]);
            mat[i][0] = mat[i][0] | (i << 1);
            mat[i][1] = mat[i][1] | (temp << 1);
        }
        Arrays.sort(mat, (o1, o2) -> {
            int a1 = o1[1] >> 1;
            int a2 = o2[1] >> 1;
            if (a1 != a2) {
                return a1 - a2;
            }
            return o1[0] >> 1 - o2[0] >> 1;
        });
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = mat[i][0] >> 1;
        }
        return result;
    }

    private int sum(int[] arr) {
        int result = 0;
        int size = arr.length;
        for (int i = 0; i < size && arr[i] == 1; i++) {
            result += arr[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.kWeakestRows(new int[][]{{1, 0, 0, 0}, {1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}}, 2);
    }
}