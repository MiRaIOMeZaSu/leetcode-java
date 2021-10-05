package leetcode.editor._14_longestCommonPrefix;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        int size = strs.length;
        while (true) {
            if (index >= strs[0].length()) {
                return stringBuilder.toString();
            }
            char curr = strs[0].charAt(index);
            for (int i = 1; i < size; i++) {
                if (index >= strs[i].length()) {
                    return stringBuilder.toString();
                }
                if (strs[i].charAt(index) != curr) {
                    return stringBuilder.toString();
                }
            }
            stringBuilder.append(curr);
            index++;
        }
    }
}
