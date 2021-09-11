package leetcode.editor.leetcode_cup._1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[0].length; j++) {
                if (!map.containsKey(source[i][j])) {
                    list.add(source[i][j]);
                }
                map.merge(source[i][j], 1, Integer::sum);
            }
        }
        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < target[0].length; j++) {
                map1.merge(target[i][j], 1, Integer::sum);
            }
        }
        int total = source.length * source[0].length;
        int temp = 0;
        for (int i = 0; i < list.size(); i++) {
            int key = list.get(i);
            temp += Math.min(map.get(key), map1.getOrDefault(key, 0));
        }
        return total - temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minimumSwitchingTimes(new int[][]{{1,3},{5,4}}, new int[][]{{3,1},{5,4}});
    }
}