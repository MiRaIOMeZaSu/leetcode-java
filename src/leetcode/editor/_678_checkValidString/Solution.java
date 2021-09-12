package leetcode.editor._678_checkValidString;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean checkValidString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('(', 0);
        map.put(')', 1);
        map.put('*', 2);
        int size = s.length();
        int[][] fore = new int[s.length()][3];
        int[][] tail = new int[s.length()][3];
        fore[0][map.get(s.charAt(0))] += 1;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < 3; j++) {
                fore[i][j] = fore[i - 1][j];
            }
            fore[i][map.get(s.charAt(i))]++;
        }
        for (int i = size - 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                fore[i][j] = fore[i - 1][j];
            }
            fore[i][map.get(s.charAt(i))]++;
        }
        return false;
    }
}