package leetcode.editor._342_isPowerOfFour;

class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        int time = 0;
        while (time != 1) {
            time = 1;
            int a = 4;
            while (n % a == 0) {
                n /= a;
                if (a * a == 0) {
                    continue;
                }
                a *= a;
                time *= 2;
            }
        }
        return n == 1 ? true : false;
    }
}