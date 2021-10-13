package leetcode.editor._456_find132pattern;

import java.util.Map;
import java.util.TreeMap;

class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 2; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int _min = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > _min) {
                Map.Entry<Integer, Integer> ret = map.lowerEntry(nums[i]);
                if (ret != null && ret.getValue() > 0 && ret.getKey() > _min) {
                    return true;
                }
            } else {
                _min = nums[i];
            }
            int count = map.get(nums[i + 1]);
            if (count == 1) {
                map.remove(nums[i + 1]);
            } else {
                map.put(nums[i + 1], count - 1);
            }
        }
        return false;
    }
}