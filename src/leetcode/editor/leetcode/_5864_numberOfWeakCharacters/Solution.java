package leetcode.editor._5864_numberOfWeakCharacters;

import java.util.*;

class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        int size = properties.length;
        Arrays.sort(properties, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            map.merge(properties[i][1], 1, Integer::sum);
        }
        int last = -1;
        int result = 0;
        for (int i = 0; i < size; i++) {
            if (last != properties[i][0]) {
                for (int j = i; j < size && properties[j][0] == properties[i][0]; j++) {
                    map.merge(properties[j][1], -1, Integer::sum);
                }
                last = properties[i][0];
            }
            while (!map.isEmpty() && map.lastEntry().getValue() <= 0) {
                map.pollLastEntry();
            }
            if (map.isEmpty()) {
                break;
            }
            Integer temp = map.lastKey();
            if (temp != null && temp > properties[i][1]) {
                result += 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.numberOfWeakCharacters(new int[][]{{1, 5}, {10, 4}, {4, 3}});
        System.out.println(ret);
    }
}