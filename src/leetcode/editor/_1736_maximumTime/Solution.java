package leetcode.editor._1736_maximumTime;

class Solution {
    char signal = '?';

    public String maximumTime(String time) {
        char[] chars = time.toCharArray();
        if (chars[0] == signal) {
            if (chars[1] == signal) {
                chars[0] = '2';
                chars[1] = '3';
            } else if (chars[1] > '3') {
                chars[0] = '1';
            } else {
                chars[0] = '2';
            }
        }
        if (chars[1] == signal) {
            if (chars[0] == '2') {
                chars[1] = '3';
            } else {
                chars[1] = '9';
            }
        }
        if (chars[4] == signal) {
            chars[4] = '9';
        }
        if (chars[3] == signal) {
            chars[3] = '5';
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String string = solution.maximumTime("??:3?");
        System.out.println(string);
    }
}