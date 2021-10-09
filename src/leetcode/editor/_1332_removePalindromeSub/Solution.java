package leetcode.editor._1332_removePalindromeSub;

class Solution {
    public int removePalindromeSub(String s) {
        // 每次直接寻找最长回文子序列
        // 最长为1000
        char[] chars = s.toCharArray();
        int size = chars.length;
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            if (chars[left] != chars[right]) {
                return 2;
            }
            left++;
            right--;
        }
        return 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.removePalindromeSub("abbaaaab");
    }
}