package leetcode.editor._5878_longestSubsequenceRepeatedK;

class Solution {
    static final private char a = 'a';
    String ans = "";

    public String longestSubsequenceRepeatedK(String s, int k) {
        // 重点是重复k次(超过k次依旧合法,但当做k字计算),然后在所有重复k次中寻找最长
        // 字符长度为16000
        // 动态规划法,目标字符越来越长
        // 结果字符串长度最长为8
        // 计算右边的数量

        // 回溯法的选择与否和字节跳转表
        int size = s.length();
        int[][] table = new int[size][26];
        table[size - 1][s.charAt(size - 1) - a] = 1;
        for (int i = size - 2; i >= 0; i--) {
            System.arraycopy(table[i + 1], 0, table[i], 0, 26);
            table[i][s.charAt(i) - a] += 1;
        }
        for (int i = 0; i < 26; i++) {
            if (table[0][i] >= k) {
                char ch = (char) (a + i);

            }
        }
        return ans;
    }

    private void solve(String currString) {

    }
}