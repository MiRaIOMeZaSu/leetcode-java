package leetcode.editor.leetcode._69_mySqrt;

class Solution {
    public int mySqrt(int x) {
        // 使用二分法
        if (x <= 1) {
            return x;
        }
        int left = 1;
        int right = x - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            long curr = (long) mid * (long) mid;
            long next = ((long) mid + 1) * ((long) mid + 1);
            if (curr <= x && next > x) {
                return mid;
            } else if (curr < x) {
                left = mid + 1;
            } else if (curr > x) {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.mySqrt(1000000000));
    }
}