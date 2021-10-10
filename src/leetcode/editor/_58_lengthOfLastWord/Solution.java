package leetcode.editor._58_lengthOfLastWord;

class Solution {
    public int lengthOfLastWord(String s) {
        int size = s.length();
        if (size == 1) {
            return 1;
        }
        int right = size - 1;
        while (s.charAt(right) == ' ') {
            right--;
        }
        int left = right;
        while (left >= 0 && s.charAt(left) != ' ') {
            left--;
        }
        if (left < 0) {
            left = 0;
        }
        if (s.charAt(left) == ' ') {
            left++;
        }
        return right - left + 1;
    }
}