package leetcode.editor._29_divide;

class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        int flag = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? 1 : -1;
        int _dividend = Math.abs(dividend);
        int _divisor = Math.abs(divisor);
        if (_divisor == 1) {
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }
            int ret = flag < 0 ? -_dividend : _dividend;
        }
        int time = 1;
        int curr = _divisor;
        if (_dividend - curr > _divisor) {
            _dividend -= curr;
        } else if (_dividend - curr >= 0) {
            return flag < 0 ? -1 : 1;
        } else {
            return 0;
        }
        while (_dividend - curr > _divisor) {
            curr *= 2;
            _dividend -= curr;
            time++;
        }
        int temp = 0;
        int d = (int) Math.pow(2, time - 1);
        for (int i = time; i > 0; i--) {
            temp += d;
            d /= 2;
        }
        time = temp;
        if (_dividend - _divisor < 0 && _dividend > 0) {
            return flag < 0 ? -time : time;
        }
        // 考虑二分法
        while (!(_dividend - curr < _divisor && _dividend - curr >= 0)) {
            curr -= _divisor;
        }
        time += curr / _divisor;
        return flag < 0 ? -time : time;
    }
}