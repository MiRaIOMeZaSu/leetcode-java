package leetcode.editor._541_reverseStr;

class Solution {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int size = s.length();
        for (int i = 0; i < size; i += k * 2) {
            int l = i;
            int r = Math.min(i + k - 1, size - 1);
            while (l < r) {
                char temp = chars[l];
                chars[l] = chars[r];
                chars[r] = temp;
                l++;
                r--;
            }
        }
        return new String(chars);
    }
}