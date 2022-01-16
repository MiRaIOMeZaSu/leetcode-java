package leetcode.editor.contest._5983_maxRunTime;

import java.util.Arrays;

class Solution {
    private int[] batteries;
    private long front = 0;
    private long back = 0;
    private long n;
    private int size;

    public long maxRunTime(int n, int[] batteries) {
        this.n = n;
        this.batteries = batteries;
        // n的数量小于等于batteries的长度
        this.size = batteries.length;
        // 必须是n台同时,少了不行
        Arrays.sort(batteries);
        if (n == batteries.length) {
            return batteries[0];
        }
        // 此时n大于batteries
        // n已知,计算后面的数量,和前面的数量
        for (int i = 0; i < n; i++) {
            front += batteries[i];
        }
        for (int i = n; i < size; i++) {
            back += batteries[i];
        }
        // 等长的木棒问题!(木棒可以掰开!)
        // 二分法
        long left = batteries[0];
        long right = ((front + back) / this.n);
        long ans = left;
        while (left <= right) {
            long mid = (left + right) >> 1;
            if (satisfy(mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private boolean satisfy(long curr) {
        // 目标为curr
        long currBack = back;
        long currFront = front;
        for (int i = size - 1; i >= n && batteries[i] > curr; i--) {
            currBack -= (batteries[i] - curr);
        }
        for (int i = (int) (n - 1); i >=0 && batteries[i] > curr; i--) {
            currFront -= (batteries[i] - curr);
        }
        return currFront + currBack >= curr * n;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.maxRunTime(2, new int[]{3, 3, 3});
        System.out.println(solution.maxRunTime(3, new int[]{10, 10, 3, 5}));;
    }
}