package leetcode.editor._223_computeArea;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // 寻找相交面积和
        // 确认左右和上下
        // 先判断左右
        // 求出所有交点!
        int[][] lines = new int[8][4];
        lines[0] = new int[]{ax1, ay1, ax1, ay2};
        lines[1] = new int[]{ax1, ay1, ax2, ay1};
        lines[2] = new int[]{ax2, ay1, ax2, ay2};
        lines[3] = new int[]{ax1, ay2, ax2, ay2};
        lines[4] = new int[]{bx1, by1, bx1, by2};
        lines[5] = new int[]{bx1, by1, bx2, by1};
        lines[6] = new int[]{bx2, by1, bx2, by2};
        lines[7] = new int[]{bx1, by2, bx2, by2};
        // 相交点的数量
        List<int[]> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int[] point = findPoint(lines[i], lines[j + 4]);
                if (point != null) {
                    String key = point[0] + " " + point[1];
                    if (!set.contains(key)) {
                        list.add(point);
                        set.add(key);
                    }
                }
            }
        }
        // 已经获取到所有相交的点
        // 寻找其他在正方形中的点
        int[][][] points = new int[][][]{
                {{bx1, by1}, {bx1, by2}, {bx2, by1}, {bx2, by2}},
                {{ax1, ay1}, {ax1, ay2}, {ax2, ay1}, {ax2, ay2}}
        };
        int total = Math.abs(ax1 - ax2) * Math.abs(ay1 - ay2) + Math.abs(bx1 - bx2) * Math.abs(by1 - by2);
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < 4; i++) {
                int x = points[k][i][0];
                int y = points[k][i][1];
                if (x >= Math.min(points[(k + 1) % 2][0][0], points[(k + 1) % 2][2][0]) && x <= Math.max(points[(k + 1) % 2][0][0], points[(k + 1) % 2][2][0])
                        && y >= Math.min(points[(k + 1) % 2][0][1], points[(k + 1) % 2][1][1]) && y <= Math.max(points[(k + 1) % 2][0][1], points[(k + 1) % 2][1][1])
                ) {
                    String key = x + " " + y;
                    if (!set.contains(key)) {
                        list.add(points[k][i]);
                        set.add(key);
                    }
                }
            }
            // 寻找点
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i)[0] != list.get(j)[0] && list.get(i)[1] != list.get(j)[1]) {
                        int repeat = Math.abs(list.get(i)[0] - list.get(j)[0]) * Math.abs(list.get(i)[1] - list.get(j)[1]);
                        return total - repeat;
                    }
                }
            }
        }
        return total;
    }

    private int[] findPoint(int[] l1, int[] l2) {
        if (l1[0] == l1[2] && l2[0] == l2[2]) {
            // 平行竖线
            if (l1[0] == l2[0]) {
                // 重合线
                if (Math.max(l1[1], l1[3]) >= Math.max(l2[1], l2[3])) {
                    // l1在上
                    if (Math.max(l1[1], l1[3]) <= Math.min(l2[1], l2[3])) {
                        return null;
                    }
                    return new int[]{l1[0], Math.max(l2[1], l2[3])};
                } else {
                    // l2在上
                    if (Math.min(l1[1], l1[3]) >= Math.max(l2[1], l2[3])) {
                        return null;
                    }
                    return new int[]{l1[0], Math.max(l1[1], l1[3])};
                }
            }
            return null;
        }
        if (l1[1] == l1[3] && l2[1] == l2[3]) {
            // 平行横线
            if (l1[1] == l2[1]) {
                // 重合线
                if (Math.min(l1[0], l1[2]) <= Math.min(l2[0], l2[2])) {
                    // l1在前
                    if (Math.max(l1[0], l1[2]) < Math.min(l2[0], l2[2])) {
                        return null;
                    }
                    return new int[]{Math.min(l2[0], l2[2]), l1[1]};
                } else {
                    // l2在前
                    if (Math.min(l1[0], l1[2]) > Math.max(l2[0], l2[2])) {
                        return null;
                    }
                    return new int[]{Math.min(l1[0], l1[2]), l1[1]};
                }
            }
            return null;
        }
        // 垂直
        if (l1[0] == l1[2]) {
            return getInts(l2, l1);
        } else {
            return getInts(l1, l2);
        }
    }

    private int[] getInts(int[] l1, int[] l2) {
        if (l2[0] >= Math.max(l1[0], l1[2]) || l2[0] <= Math.min(l1[0], l1[2])) {
            return null;
        }
        if (Math.min(l2[1], l2[3]) > l1[1] || Math.max(l2[1], l2[3]) < l1[1]) {
            return null;
        }
        return new int[]{l2[0], l1[1]};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.computeArea(-2,
                -2,
                2,
                2,
                -3,
                -3,
                3,
                -1);
        System.out.println(ans);
    }
}
