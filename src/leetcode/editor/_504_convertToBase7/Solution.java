package leetcode.editor._504_convertToBase7;

class Solution {
    public String convertToBase7(int num) {
        String ret = "";
        boolean isNegative = false;
        if (num < 0) {
            isNegative = true;
            num = -num;
        }
        int a = 7;
        while (num / a > 0) {
            a *= 7;
        }
        a /= 7;
        while (a >= 7) {
            ret += String.valueOf(num / a);
            num %= a;
            a /= 7;
        }
        ret += String.valueOf(num / a);
        return isNegative ? "-" + ret : ret;
    }
}