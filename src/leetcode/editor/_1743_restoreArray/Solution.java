package leetcode.editor._1743_restoreArray;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        int size = adjacentPairs.length;
        Map<Integer, int[]> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            int[] temp = new int[]{adjacentPairs[i][0], adjacentPairs[i][1]};
            for (int j = 0; j < 2; j++) {
                int a = temp[j % 2];
                int b = temp[(j + 1) % 2];
                if (!map.containsKey(a)) {
                    map.put(a, new int[]{b, Integer.MIN_VALUE});
                    set.add(a);
                } else {
                    int[] arr = map.get(a);
                    if (arr[1] == Integer.MIN_VALUE) {
                        arr[1] = b;
                        set.remove(a);
                    }
                }
            }
        }
        int resultSize = size + 1;
        int[] result = new int[size + 1];

        result[0] = set.iterator().next();
        int index = 1;
        while (index < resultSize) {
            int[] near = map.get(result[index - 1]);
            int next = near[0];
            if (index - 2 >= 0 && next == result[index - 2]) {
                next = near[1];
            }
            result[index] = next;
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.restoreArray(new int[][]{});
    }
}