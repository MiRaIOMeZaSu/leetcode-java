package leetcode.editor._1332_removePalindromeSub;

class Solution {
    public int removePalindromeSub(String s) {
        // 每次直接寻找最长回文子序列
        // 最长为1000
        int[] bits = new int[]{1, 2};
        int size = s.length();
        char[] chars = s.toCharArray();
        int currBit = 0;
        for (int i = 0; i < size && currBit != 3; i++) {
            if (chars[i] == 'a') {
                currBit |= bits[0];
            } else {
                currBit |= bits[1];
            }
        }
        if (currBit == 3) {
            // 判断本身是否为回文

            return 2;
        }
        return 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.removePalindromeSub("abbaaaab");
    }
}