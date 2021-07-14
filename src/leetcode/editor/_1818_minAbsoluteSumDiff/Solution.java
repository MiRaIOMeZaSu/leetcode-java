package leetcode.editor._1818_minAbsoluteSumDiff;

import java.util.*;

class Solution {
    int pivot = 1000000007;

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int size = nums1.length;
        if (size == 1) {
            return Math.abs(nums1[0] - nums2[0]);
        }
        long total = 0;
        int[][] arr = new int[size][3];
        for (int i = 0; i < size; i++) {
            arr[i][0] = nums1[i];
            arr[i][1] = nums2[i];
            arr[i][2] = Math.abs(nums2[i] - nums1[i]);
            total += arr[i][2];
        }
        if (total == 0) {
            return (int) (total % pivot);
        }
        Arrays.sort(arr, (o1, o2) -> o2[2] - o1[2]);
        TreeSet<Integer> set = new TreeSet<>();
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            set.add(nums1[i]);
            hashSet.add(nums1[i]);
        }
        long div = 0;
        for (int i = 0; i < size; i++) {
            int diff = arr[i][2];
            if (div >= diff) {
                break;
            }
            if (hashSet.contains(arr[i][1])) {
                return (int) ((total - Math.max(arr[i][2], div)) % pivot);
            }
            int lowDiff = Integer.MAX_VALUE;
            int highDiff = Integer.MAX_VALUE;
            Integer low = set.lower(arr[i][1]);
            if (low != null) {
                lowDiff = Math.abs(low - arr[i][1]);
            }
            Integer high = set.higher(arr[i][1]);
            if (high != null) {
                highDiff = Math.abs(high - arr[i][1]);
            }
            div = Math.max(div, arr[i][2] - Math.min(highDiff, lowDiff));
        }
        return (int) ((total - div) % pivot);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minAbsoluteSumDiff(new int[]{1, 7, 5}, new int[]{2, 3, 5});
    }
}