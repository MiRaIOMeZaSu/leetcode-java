package leetcode.editor._5864_numberOfWeakCharacters;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        int size = properties.length;
        Arrays.sort(properties, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        for (int i = 1; i < size; i++) {
            if (properties[i][0] <= properties[0][0] || properties[i][1] <= properties[0][1]) {
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.numberOfWeakCharacters(new int[][]{{1, 5}, {10, 4}, {4, 3}});
    }
}