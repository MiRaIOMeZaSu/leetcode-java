package leetcode.editor.offer._58_reverseLeftWords;

class Solution {
    public String reverseLeftWords(String s, int n) {
        String a = s.substring(0, n - 1);
        String b = s.substring(n);
        return b + a;
    }
}