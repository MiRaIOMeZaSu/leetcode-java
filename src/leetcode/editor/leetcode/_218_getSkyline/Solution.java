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
        Map<Integer, int[]> map = new HashMap<>();
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
                map.computeIfAbsent(x, k -> new int[]{-1,-1,-1});
                int index = isLeft ? 0 : 1;
                int [] arr = map.get(x);
                arr[index] = Math.max(arr[index],y);
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
                int [] arr = map.get(x);
                arr[2] = Math.max(arr[2],y);
            }
        }
        for (int i = 0; i < X_list.size(); i++) {
            int key = X_list.get(i);
            int[] arr = map.get(key);
            int max1 = arr[0];
            int max2 = arr[1];
            int max3 = arr[2]==-1?0:arr[2];
            int y;
            if (max1 == max2 || Math.max(max1, max2) < max3 || max3 == max1 || max3 == max2) {
                continue;
            }

            if (max1 != -1 && max2 != -1) {
                // 不相等且均存在
                if (Math.min(max1, max2) > max3) {
                    y = max1;
                } else if (max3 > max1) {
                    y = max3;
                } else {
                    y = max1;
                }
            } else if (max1 == -1) {
                y = max3;
            } else {
                y = Math.max(max1, max2);
            }
            List<Integer> temp = new ArrayList<>();
            temp.add(key);
            temp.add(y);
            result.add(temp);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.getSkyline(new int[][]{{0, 3, 3}, {1, 5, 3}, {2, 4, 3}, {3, 7, 3}});
    }
}