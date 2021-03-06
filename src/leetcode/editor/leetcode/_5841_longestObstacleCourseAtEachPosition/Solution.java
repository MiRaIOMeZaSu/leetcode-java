package leetcode.editor._5841_longestObstacleCourseAtEachPosition;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int size = obstacles.length;
        int[] result = new int[size];
        result[0] = 1;
        TreeMap<Integer, Integer> map = new TreeMap<>((o1, o2) -> o2 - o1);
        int min = obstacles[0];
        map.put(1,obstacles[0]);
        for (int i = 1; i < size; i++) {
            result[i] = 1;
            min = Math.min(min, obstacles[i - 1]);
            if (obstacles[i] < min) {
                map.merge(result[i], obstacles[i], Integer::min);
                continue;
            }
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
                if (entry.getValue() <= obstacles[i]) {
                    result[i] = entry.getKey() + 1;
                    break;
                }
            }
            map.merge(result[i], obstacles[i], Integer::min);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.longestObstacleCourseAtEachPosition(new int[]{2,2,1});
    }
}