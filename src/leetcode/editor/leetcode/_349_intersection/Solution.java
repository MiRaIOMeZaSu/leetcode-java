package leetcode.editor._349_intersection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    List<Integer> list;

    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        list = new ArrayList<>();
        int a = 0;
        int b = 0;
        while (a < nums1.length && b < nums2.length) {
            while (a + 1 < nums1.length && nums1[a] == nums1[a + 1]) {
                a++;
            }
            while (b + 1 < nums2.length && nums2[b] == nums2[b + 1]) {
                b++;
            }
            if (nums1[a] == nums2[b]) {
                push(nums1[a]);
                a++;
                b++;
            } else if (nums1[a] < nums2[b]) {
                a++;
            } else {
                b++;
            }
        }
        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }

    private void push(int num) {
        if (list.isEmpty() || list.get(list.size() - 1) != num) {
            list.add(num);
        }
    }
}