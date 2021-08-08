package leetcode.editor._5841_longestObstacleCourseAtEachPosition;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int size = obstacles.length;
        int[] result = new int[size];
        result[0] = 1;
        TreeMap<Integer, Integer> map = new TreeMap<>((o1, o2) -> o2 - o1);
        int min = obstacles[0];
        map.put(obstacles[0], 1);
        for (int i = 1; i < size; i++) {
            result[i] = 1;
            min = Math.min(min, obstacles[i - 1]);
            if (obstacles[i] < min) {
                Integer newValue = map.merge(obstacles[i], result[i], Integer::max);
                continue;
            }
            Map.Entry<Integer, Integer> entry = map.higherEntry(obstacles[i] + 1);
            if (entry != null) {
                result[i] = entry.getValue() + 1;
                for (int j = i - 1; j >= 0; j--) {
                    if (obstacles[j] == entry.getKey()) {
                        break;
                    }
                    if (obstacles[j] <= obstacles[i]) {
                        result[i] = Math.max(result[i], result[j] + 1);
                    }
                }
            }
            Integer newValue = map.merge(obstacles[i], result[i], Integer::max);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.longestObstacleCourseAtEachPosition(new int[]{5, 1, 5, 5, 1, 3, 4, 5, 1, 4});
    }
}