package leetcode.editor.contest._6010_minimumTime;

import java.util.Arrays;

class Solution {
    public long minimumTime(int[] time, int totalTrips) {
        // 二分法
        Arrays.sort(time);
        long left = time[0];
        long right = (long) totalTrips * (long) time[0];
        long ans = right;
        while (left <= right) {
            long mid = (left + right) >> 1;
            // 判断mid是否满足
            int times = 0;
            for (int i = 0; i < time.length && time[i] <= mid; i++) {
                times += mid / (long) time[i];
            }
            if (times >= totalTrips) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minimumTime(new int[]{10000000}, 1));
    }
}