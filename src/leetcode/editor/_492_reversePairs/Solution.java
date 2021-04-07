package leetcode.editor._492_reversePairs;

import java.util.*;

class Solution {
    public int reversePairs(int[] nums) {
        int ret = 0;
        HashMap<Integer, Integer> map = new HashMap<>(60);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int index = nums.length - 1;
        Integer[] set = map.keySet().toArray(new Integer[0]);
        Arrays.sort(set, Collections.reverseOrder());
        while (map.size() != 0) {
            map.put(nums[index], map.get(nums[index]) - 1);
            if (map.get(nums[index]) == 0) {
                map.remove(nums[index]);
            }
            if (set.length*3 > map.size() * 5) {
                set = map.keySet().toArray(new Integer[0]);
                Arrays.sort(set, Collections.reverseOrder());
            }
            for (int i = 0; i < set.length; i++) {
                if (Math.ceil((double) set[i] / (double) 2) > nums[index]) {
                    if (map.containsKey(set[i])) {
                        ret += map.get(set[i]);
                    }
                } else {
                    break;
                }
            }
            index--;
        }
        return ret;
    }
}