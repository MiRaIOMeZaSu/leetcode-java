package leetcode.editor._5841_longestObstacleCourseAtEachPosition;

class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int size = obstacles.length;
        int[] result = new int[size];
        result[0] = 1;
        int min = obstacles[0];
        for (int i = 1; i < size; i++) {
            result[i] = 1;
            min = Math.min(min, obstacles[i - 1]);
            if (obstacles[i] < min) {
                continue;
            }
            for (int j = i - 1; j >= 0; j--) {
                if (obstacles[i] >= obstacles[j]) {
                    result[i] = result[j] + 1;
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.longestObstacleCourseAtEachPosition(new int[]{5, 1, 5, 5, 1, 3, 4, 5, 1, 4});
    }
}