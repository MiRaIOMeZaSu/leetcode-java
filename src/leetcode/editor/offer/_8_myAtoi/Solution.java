package leetcode.editor.offer._8_myAtoi;

class Solution {
    public int myAtoi(String s) {
        int index = 0;
        int size = s.length();
        while (index < size && s.charAt(index) == ' ') {
            index++;
        }
        if (index >= size) {
            return 0;
        }
        boolean isNegtive = false;
        if (s.charAt(index) == '+') {
            index++;
        } else if (s.charAt(index) == '-') {
            isNegtive = true;
            index++;
        }
        if (index >= size || !isNum(s.charAt(index))) {
            return 0;
        }
        long num = 0;
        while (index < size && isNum(s.charAt(index))) {
            num *= 10;
            num += s.charAt(index) - '0';
            if (num > Integer.MAX_VALUE) {
                return isNegtive ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            index++;
        }
        num = isNegtive ? -num : num;
        if (num < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (num > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) num;
    }

    private boolean isNum(char ch) {
        return ch - '0' <= 9 && ch >= '0';
    }
}