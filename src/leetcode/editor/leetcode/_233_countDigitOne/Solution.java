package leetcode.editor._233_countDigitOne;

class Solution {
    public int countDigitOne(int n) {
        // 当某一位设定为零时,两边的可能性乘积
        if (n == 0) {
            return 0;
        }
        String string = String.valueOf(n);
        int size = string.length();
        if (size == 1) {
            return 1;
        }
        int ret = 0;
        for (int i = 0; i < string.length(); i++) {
            String a = string.substring(0, i);
            String b = string.substring(i + 1, size);
            int numA = a.length() > 0 ? Integer.parseInt(a) : 0;
            // 加一表示把0包含了进去
            double temp = Math.pow(10, b.length());
            if (string.charAt(i) > '1') {
                ret += (numA + 1) * temp;
            } else if (string.charAt(i) < '1') {
                ret += numA * temp;
            } else {
                int numB = b.length() > 0 ? Integer.parseInt(b) : 0;
                ret += numA * temp + numB + 1;
            }
        }
        return ret;
    }
}