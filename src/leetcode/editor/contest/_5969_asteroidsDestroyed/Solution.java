package leetcode.editor.contest._5969_asteroidsDestroyed;

import java.util.Arrays;

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long massL = mass;
        int size = asteroids.length;
        for (int i = 0; i < size; i++) {
            if (massL >= asteroids[i]) {
                massL += asteroids[i];
            } else {
                return false;
            }
        }
        return true;
    }
}