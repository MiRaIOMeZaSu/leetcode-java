package leetcode.editor._342_isPowerOfFour;

class Solution {
    public boolean isPowerOfFour(int n) {
        // 使用位运算
        if (n == 1) {
            return true;
        }
        if (n < 4) {
            return false;
        }
        int a = n & (n - 1);
        int b = n & 0xaaaaaaaa;
        // 不仅要是2的幂还必须是偶数幂

        return a == 0 && b == 0 ? true : false;
    }
}