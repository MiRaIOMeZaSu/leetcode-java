package leetcode.editor._4_findMedianSortedArrays;

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int flag = (nums1.length + nums2.length) % 2;
        int mid1, mid2;
        if (flag == 0) {
            // 是偶数
            mid1 = (nums1.length + nums2.length) / 2;
            mid2 = (nums1.length + nums2.length) / 2 + 1;
        } else {
            // 是基数
            mid1 = (nums1.length + nums2.length + 1) / 2;
            mid2 = mid1;
        }
        if (nums1.length == 0) {
            return (nums2[mid1 - 1] + (double) nums2[mid2 - 1]) / 2;
        } else if (nums2.length == 0) {
            return (nums1[mid1 - 1] + (double) nums1[mid2 - 1]) / 2;
        }
        if (nums1.length + nums2.length == 2) {
            return (nums1[0] + (double) nums2[0]) / 2;
        }
        int a = 0;
        int b = 0;
        int curr = 0;
        int afterPivot = Integer.MIN_VALUE;
        int pivot = Integer.MIN_VALUE;
        while (curr < mid1 + 1 && a < nums1.length && b < nums2.length) {
            if (nums1[a] < nums2[b]) {
                pivot = afterPivot;
                afterPivot = nums1[a];
                a++;
            } else {
                pivot = afterPivot;
                afterPivot = nums2[b];
                b++;
            }
            curr++;
        }
        if (curr < mid1 + 1) {
            int c;
            int[] notEmpty;
            int[] empty;
            if (nums1.length == a) {
                notEmpty = nums2;
                empty = nums1;
                c = b;
            } else {
                notEmpty = nums1;
                empty = nums2;
                c = a;
            }
            pivot = afterPivot;
            afterPivot = notEmpty[mid1 - curr + c];
            if (mid1 - curr + c - 1 >= 0 && notEmpty[mid1 - curr + c - 1] > pivot) {
                pivot = notEmpty[mid1 - curr + c - 1];
            }
        }
        if (flag == 0) {
            return (pivot + (double) afterPivot) / 2;
        }
        return pivot;
    }
}
