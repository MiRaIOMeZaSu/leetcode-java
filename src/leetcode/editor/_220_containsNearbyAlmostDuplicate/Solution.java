package leetcode.editor._220_containsNearbyAlmostDuplicate;


import java.util.*;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 维护一个大顶堆和一个小顶堆
        // 只变换k
        if (k == 0 || nums.length <= 1) {
            return false;
        }
        TreeMap<Integer, Integer> minTreeMap = new TreeMap<>();
//        TreeMap<Integer, Integer> maxTreeMap = new TreeMap<>((o1, o2) -> o2 - o1);
        List<TreeMap<Integer, Integer>> maps = new ArrayList<>();
        maps.add(minTreeMap);
//        maps.add(maxTreeMap);
        for (int i = 1; i <= k && i < nums.length; i++) {
            int num = nums[i];
            minTreeMap.put(num, minTreeMap.getOrDefault(num, 0) + 1);
//            maxTreeMap.put(num, maxTreeMap.getOrDefault(num, 0) + 1);
        }
        for (int i = 0; i < nums.length - 1; i++) {
            // 如何避免溢出?
            int maxBorder = nums[i] + t;
            int minBorder = nums[i] - t;
            if (nums[i] > Integer.MAX_VALUE - t) {
                // 正数溢出(此时必然不会有负溢出)
                return minTreeMap.higherKey(minBorder - 1) != null;
            }
            if (nums[i] < t + Integer.MIN_VALUE) {
                // 负数溢出(此时必然不会有正溢出)
                return minTreeMap.lowerKey(maxBorder + 1) != null;
            }
            if (maxBorder > minTreeMap.firstKey()) {
                Object temp = minTreeMap.higherKey(minBorder - 1);
                if (temp != null) {
                    int tempNum = (int) temp;
                    if (tempNum <= maxBorder) {
                        return true;
                    }
                }
            } else if (maxBorder == minTreeMap.firstKey()) {
                return true;
            }
            // 移除操作
            int numToRemove = nums[i + 1];
            int temp;
            for (int j = 0; j < maps.size(); j++) {
                TreeMap<Integer, Integer> map = maps.get(j);
                temp = map.get(numToRemove);
                if (temp == 1) {
                    map.remove(numToRemove);
                } else {
                    map.put(numToRemove, temp - 1);
                }
            }
            if (i + k + 1 < nums.length) {
                // 添加操作
                int numToAdd = nums[i + k + 1];
                for (int j = 0; j < maps.size(); j++) {
                    TreeMap<Integer, Integer> map = maps.get(j);
                    map.put(numToAdd, map.getOrDefault(numToAdd, 0) + 1);
                }
            }
        }
        return false;
    }
}