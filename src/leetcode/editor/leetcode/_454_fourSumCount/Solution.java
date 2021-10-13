package leetcode.editor._454_fourSumCount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 缩减一个或多个后当成三数相加或二数相加
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        sum(nums1, nums2, list1, map1);
        sum(nums3, nums4, list2, map2);
        int ans = 0;
        for (int i = 0; i < list1.size(); i++) {
            int a = list1.get(i);
            int b = 0 - a;
            ans += map1.getOrDefault(a, 0) * map2.getOrDefault(b, 0);
        }
        return ans;
    }

    private void sum(int[] num1, int[] num2, List<Integer> list, Map<Integer, Integer> map) {
        for (int i = 0; i < num1.length; i++) {
            for (int j = 0; j < num2.length; j++) {
                int key = num1[i] + num2[j];
                int val = map.merge(key, 1, Integer::sum);
                if (val == 1) {
                    list.add(key);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2});
    }
}