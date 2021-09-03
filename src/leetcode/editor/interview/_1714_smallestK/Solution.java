package leetcode.editor.interview._1714_smallestK;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

class Solution {
    public int[] smallestK(int[] arr, int k) {
        // 使用bfprt解决
        // 划分5数一组
        // 获取中位数
        // 获取中位数的中位数直到获取到确切的数字
        // 判断取得数字的位置并比较和递归
        if (k == 0) {
            return new int[0];
        }
        int left = 0;
        int right = arr.length - 1;
        while (true) {
            int middle = getLargeMiddle(arr, left, right);
            int[] indexs = patitation(arr, middle, left, right);
            if (k < indexs[0]) {
                right = indexs[0] - 1;
            } else if (k > indexs[1]) {
                left = indexs[1] + 1;
            } else {
                break;
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    private int[] patitation(int[] arr, int number, int start, int end) {
        int left = start;
        int right = end;
        int curr = left;
        while (curr <= right && left < right) {
            if (arr[curr] < number) {
                swap(arr, curr, left);
                left++;
            } else if (arr[curr] > number) {
                swap(arr, curr, right);
                right--;
                continue;
            }
            curr++;
        }
        return new int[]{left, right};
    }

    private void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private int getLargeMiddle(int[] arr, int start, int end) {
        int size = end - start + 1;
        if (size <= 5) {
            return getLittleMiddle(arr, start, end);
        }
        int count = size / 5 + (size % 5 == 0 ? 0 : 1);
        int[] middles = new int[count];
        for (int i = 0; i < count; i += 1) {
            int tail = i * 5 + 4 + start;
            if (tail > end) {
                tail = end;
            }
            middles[i] = getLittleMiddle(arr, i * 5 + start, tail);
        }
        return getLargeMiddle(middles, 0, count - 1);
    }

    private int getLittleMiddle(int[] arr, int start, int end) {
        // 获取中位数
        int size = end - start + 1;
        Arrays.sort(arr, start, end);
        return arr[start + (size >> 1)];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] temp = new int[]{2, 1, 3, 1, 2, 5, 7, 8, 7, 5, 6, 7, 8, 9, 0, 1, 2, 4, 5, 11, 22, 33, 51, 2, 51, 21, 55, 75, 3, 47, 89, 3, 1, 2};
        int[] result = solution.smallestK(temp, 15);
        System.out.println(result);
    }
}