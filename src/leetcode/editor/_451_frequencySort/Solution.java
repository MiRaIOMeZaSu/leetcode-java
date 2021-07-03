package leetcode.editor._451_frequencySort;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String frequencySort(String s) {
        int[][] count = new int[95][2];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - ' ';
            count[index][0]++;
            count[index][1] = index;
        }

        Arrays.sort(count, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        int index = 0;
        StringBuilder builder = new StringBuilder();
        while (index < 95 && count[index][0] > 0) {
            for (int i = 0; i < count[index][0]; i++) {
                builder.append((char)(' ' + count[index][1]));
            }
            index++;
        }
        return builder.toString();
    }
}