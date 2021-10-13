package leetcode.editor._50_myPow;

class Solution {
    public double myPow(double x, int n) {
        boolean isNegative = false;
        if (n == 0) {
            return 1;
        }
        double ret = 1;
        if (n < 0) {
            isNegative = true;
            if (n == Integer.MIN_VALUE) {
                ret *= x;
                n += 1;
            }
            n = Math.abs(n);
        }
        // 此时n为正数
        double temp = 1;
        while (n > 0) {
            double pivot = x;
            int i = 1;
            while (n >= i) {
                temp *= pivot;
                n -= i;
                i *= 2;
                pivot *= pivot;
            }
        }
        ret *= temp;
        if (isNegative) {
            ret = 1 / ret;
        }
        return ret;
    }
}