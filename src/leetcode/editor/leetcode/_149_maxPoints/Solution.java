package leetcode.editor._149_maxPoints;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maxPoints(int[][] points) {
        // 使用a和b标记一个线条
        int size = points.length;
        if (size < 3) {
            return size;
        }
        int ret = 2;
        HashMap<Integer, Integer> special = new HashMap<>();
        HashMap<Double, HashMap<Double, Set<Integer>>> lines = new HashMap<>();
        for (int i = 0; i < size; i++) {
            special.put(points[i][0], special.getOrDefault(points[i][0], 0) + 1);
            ret = Math.max(ret, special.get(points[i][0]));
            for (int j = i + 1; j < size; j++) {
                if (points[i][0] == points[j][0]) {
                    continue;
                }
                double[] ab = getLine(points[i][0], points[i][1], points[j][0], points[j][1]);
                if (!lines.containsKey(ab[0])) {
                    lines.put(ab[0], new HashMap<>());
                }
                if (!lines.get(ab[0]).containsKey(ab[1])) {
                    lines.get(ab[0]).put(ab[1], new HashSet<>());
                }
                lines.get(ab[0]).get(ab[1]).add(i);
                lines.get(ab[0]).get(ab[1]).add(j);
                ret = Math.max(ret, lines.get(ab[0]).get(ab[1]).size());
            }
        }
        return ret;
    }

    private double[] getLine(int x1, int y1, int x2, int y2) {
        double[] ret = new double[2];
        ret[0] = (double) (y2 - y1) / (double) (x2 - x1);
        ret[1] = y1 - ret[0] * x1;
        return ret;
    }
}