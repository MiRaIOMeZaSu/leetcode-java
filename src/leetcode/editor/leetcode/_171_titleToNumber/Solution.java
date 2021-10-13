package leetcode.editor._171_titleToNumber;

class Solution {
    public int titleToNumber(String columnTitle) {
        int size = columnTitle.length();
        int result = 0;
        int bit = 1;
        for (int i = size - 1; i >= 0; i--) {
            result += bit * (columnTitle.charAt(i) - 'A' + 1);
            bit *= 26;
        }
        return result;
    }
}