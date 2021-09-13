package leetcode.editor._447_numberOfBoomerangs;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numberOfBoomerangs(int[][] points) {
        // 计算每个点与其他点的位置距离
        int size = points.length;
        Map<Integer, Integer>[] maps = new HashMap[size];
        for (int i = 0; i < size; i++) {
            if (maps[i] == null) {
                maps[i] = new HashMap<>();
            }
            for (int j = i + 1; j < size; j++) {
                if (maps[j] == null) {
                    maps[j] = new HashMap<>();
                }
                int distance = getDistance(points[i][0], points[i][1], points[j][0], points[j][1]);
                maps[i].merge(distance, 1, Integer::sum);
                maps[j].merge(distance, 1, Integer::sum);
            }
        }
        int result = 0;
        for (int i = 0; i < size; i++) {
            for (int key : maps[i].keySet()
            ) {
                int val = maps[i].get(key);
                if (val >= 2) {
                    // 任选两个
                    result += choice2ofAll(val) * 2;
                }
            }
        }
        return result;
    }

    private int getDistance(int x1, int y1, int x2, int y2) {
        return (int) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private int choice2ofAll(int total) {
        return ((total - 1) * total) >> 1;
    }
}