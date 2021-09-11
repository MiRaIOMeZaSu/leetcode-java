package leetcode.editor.leetcode_cup._4;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int circleGame(int[][] toys, int[][] circles, int r) {
        int[][] toysA = new int[toys.length][];
        int[][] toysB = new int[toys.length][];
        int[][] toysC = toys;
        System.arraycopy(toys, 0, toysB, 0, toys.length);
        System.arraycopy(toys, 0, toysA, 0, toys.length);
        Arrays.sort(toysA, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(toysB, (o1, o2) -> o1[1] - o2[1]);
        Arrays.sort(toysC, (o1, o2) -> o1[2] - o2[2]);


        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.circleGame(new int[][]{{3, 3, 1}, {3, 2, 1}}, new int[][]{{4, 3}}, 2);
    }
}