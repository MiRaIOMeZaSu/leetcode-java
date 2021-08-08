package leetcode.editor._5838_isPrefixString;

class Solution {
    public boolean isPrefixString(String s, String[] words) {
        int size = s.length();
        int j = 0;
        int x = 0;
        int i = 0;
        for (; i < size; i++) {
            if (j >= words.length) {
                break;
            }
            if (words[j].charAt(x) != s.charAt(i)) {
                return false;
            }
            x++;
            if (words[j].length() == x) {
                x = 0;
                j++;
            }
        }
        return i == size - 1 && x == 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.isPrefixString("z",
                new String[]{"z"});
    }
}