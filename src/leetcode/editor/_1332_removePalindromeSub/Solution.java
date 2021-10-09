package leetcode.editor._1332_removePalindromeSub;

class Solution {
    public int removePalindromeSub(String s) {
        // 每次直接寻找最长回文子序列
        // 最长为1000
        int size = s.length();
        if (size == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int right = size - 1;
        boolean[] toRemove = new boolean[size];
        while (left <= right) {
            if (chars[left] == chars[right]) {
                toRemove[left] = true;
                toRemove[right] = true;
                left++;
                right--;
            } else {
                right--;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (!toRemove[i]) {
                stringBuilder.append(chars[i]);
            }
        }
        return 1 + removePalindromeSub(stringBuilder.toString());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.removePalindromeSub("abbaaaab");
    }
}