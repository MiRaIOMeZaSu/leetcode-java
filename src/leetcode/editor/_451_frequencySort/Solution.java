package leetcode.editor._451_frequencySort;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String frequencySort(String s) {
        char[] chars = new char[52];
        chars[0] = 'A';
        chars[26] = 'a';
        for (int i = 0; i < 25; i++) {
            chars[i + 1]  = (char) (chars[i] + 1);
            chars[i + 27]  = (char) (chars[i + 26] + 1);
        }
        int[][] count = new int[52][2];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'A';
            if (index > 25) {
                index += 26 - 32;
            }
            count[index][0]++;
        }
        for (int i = 0; i < 52; i++) {
            count[i][1] = i;
        }
        Arrays.sort(count, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        int index = 0;
        StringBuilder builder = new StringBuilder();
        while (index < 52 && count[index][0] > 0) {
            for (int i = 0; i < count[index][0]; i++) {
                builder.append(chars[count[index][1]]);
            }
            index++;
        }
        return builder.toString();
    }
}