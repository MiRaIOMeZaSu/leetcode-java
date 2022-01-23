package leetcode.editor.contest._5990_findLonely;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(16);
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            map.merge(nums[i], 1, Integer::sum);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if (map.get(key) == 1
                    && map.getOrDefault(key - 1, 0) == 0
                    && map.getOrDefault(key + 1, 0) == 0
            ) {
                ans.add(key);
            }
        }
        return ans;
    }
}