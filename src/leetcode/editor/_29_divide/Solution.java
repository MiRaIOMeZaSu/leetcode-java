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
        int _dividend = dividend > 0 ? dividend : -dividend;
        int _divisor = divisor > 0 ? divisor : -divisor;
        if (_divisor == 1) {
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }
            return flag < 0 ? -_dividend : _dividend;
        }

        // 循环判断开始
        int count = 0;
        int curr = _divisor;
        while (!(_dividend < _divisor && _dividend >= 0)) {
            if (_dividend - _divisor > _divisor) {
                _dividend -= _divisor;
            } else if (_dividend - _divisor >= 0) {
                return flag < 0 ? -(count + 1) : 1 + count;
            } else {
                return count;
            }
            int d = 1;
            count += 1;
            while (_dividend - curr > curr) {
                curr += curr;
                _dividend -= curr;
                d += d;
                count += d;
            }
            // 此时_dividend必定大于零
            curr = _divisor;
        }
        return flag < 0 ? -count : count;
    }
}