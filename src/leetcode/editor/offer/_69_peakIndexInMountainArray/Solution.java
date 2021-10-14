package leetcode.editor.offer._69_peakIndexInMountainArray;

class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        // 使用二分法
        int size = arr.length;
        int left = 0;
        int right = size - 1;
        if (arr[left] > arr[left + 1]) {
            return left;
        }
        if (arr[right] > arr[right - 1]) {
            return right;
        }
        left++;
        right--;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] <= arr[mid + 1]) {
                // 左山坡
                left = mid + 1;
            } else if (arr[mid] <= arr[mid - 1]) {
                // 右山坡
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return left;
    }
}