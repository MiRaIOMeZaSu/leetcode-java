package leetcode.editor._218_getSkyline;

import java.util.*;


class Solution {

    class Point {
        boolean isLeft;
        int val;
        boolean isBorder = true;

        Point(int val, boolean isLeft) {
            this.val = val;
            this.isLeft = isLeft;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        // 一共三种情况
        List<List<Integer>> result = new ArrayList<>();
        int size = buildings.length;
        Set<Integer> X_set = new HashSet<>();
        List<Integer> X_list = new ArrayList<>();
        Map<Integer, List<Integer>[]> map = new HashMap<>();
        // 必须同时存储是左右位置
        for (int i = 0; i < size; i++) {
            int y = buildings[i][2];
            for (int j = 0; j < 2; j++) {
                int x = buildings[i][j];
                if (!X_set.contains(x)) {
                    X_set.add(x);
                    X_list.add(x);
                }
                boolean isLeft = (j == 0);
                map.computeIfAbsent(x, k -> new ArrayList[3]);
                int index = isLeft ? 0 : 1;
                if (map.get(x)[index] == null) {
                    map.get(x)[index] = new ArrayList<>();
                }
                map.get(x)[index].add(y);
            }
        }
        X_list.sort(Comparator.naturalOrder());
        Map<Integer, Integer> reflect = new HashMap<>();
        for (int i = 0; i < X_list.size(); i++) {
            reflect.put(X_list.get(i), i);
        }
        for (int i = 0; i < size; i++) {
            int y = buildings[i][2];
            int start = reflect.get(buildings[i][0]) + 1;
            int end = reflect.get(buildings[i][1]);
            for (int j = start; j < end; j++) {
                int x = X_list.get(j);
                if (map.get(x)[2] == null) {
                    map.get(x)[2] = new ArrayList<>();
                }
                map.get(x)[2].add(y);
            }
        }
        for (int i = 0; i < X_list.size(); i++) {
            int key = X_list.get(i);
            List<Integer>[] lists = map.get(key);
            int maxY = 0;
            if (lists[2] != null) {
                maxY = getMax(lists[2]);
            }
            List<Integer> temp = new ArrayList<>();
            if (lists[0] == null) {
                // 在右边,选择第二大
                int thisY = getMax(lists[1]);
                if (thisY > maxY) {
                    temp.add(key);
                    temp.add(maxY);
                    result.add(temp);
                }
            } else {
                // 在左边,选择第一大
                int max1 = getMax(lists[0]);
                int max2 = getMax(lists[1]);
                if (max1 == max2) {
                    continue;
                }
                int thisY = Math.max(max1, max2);
                if (thisY > maxY) {
                    temp.add(key);
                    temp.add(thisY);
                    result.add(temp);
                }
            }
        }
        return result;
    }

    private int getMax(List<Integer> list) {
        int result = -1;
        if (list == null) {
            return result;
        }
        for (int j = 0; j < list.size(); j++) {
            result = Math.max(list.get(j), result);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.getSkyline(new int[][]{{0, 2, 3}, {2, 5, 3}});
    }
}