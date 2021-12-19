package leetcode.editor.contest._5959_kIncreasing;

class Solution {
    public int kIncreasing(int[] arr, int k) {
        // arr[i-k] <= arr[i]
        // 数组中元素下标k之前的数一定比自己小
        // 使用线段树查看数量范围?
        int ans = 0;
        int size = arr.length;
        int[] prexMax = new int[size + 1];
        int[] afterMin = new int[size + 1];
        prexMax[0] = arr[0];
        for (int i = 1; i < size; i++) {
            prexMax[i] = Math.max(arr[i], prexMax[i - 1]);
        }
        afterMin[size - 1] = arr[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            afterMin[i] = Math.min(afterMin[i + 1], arr[i]);
        }
        prexMax[size] = prexMax[size - 1];
        afterMin[size] = arr[size - 1];
        for (int i = 0; i < size; i++) {

        }
        return ans;
    }
}