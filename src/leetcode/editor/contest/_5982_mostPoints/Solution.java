package leetcode.editor.contest._5982_mostPoints;

class Solution {
    public long mostPoints(int[][] questions) {
        // 简单的动态规划
        int size = questions.length;
        long ans = 0;
        long[] scores = new long[size];
        for (int i = 0; i < size; i++) {
            if(i != 0 && scores[i - 1] > scores[i]){
                scores[i] = scores[i - 1];
            }
            int targetIndex = i + questions[i][1] + 1;
            long targetValue = scores[i] + questions[i][0];
            if (targetIndex < size) {
                scores[targetIndex] = Math.max(scores[targetIndex], targetValue);
            }
            ans = Math.max(targetValue, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.mostPoints(new int[][]{{3, 2}, {4, 3}, {4, 4}, {2, 5}}));
        System.out.println(solution.mostPoints(new int[][]{{21, 5},{92, 3},{74, 2},{39, 4},{58, 2},{5, 5},{49, 4},{65, 3}}));
    }
}