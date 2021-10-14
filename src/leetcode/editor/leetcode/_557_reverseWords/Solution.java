package leetcode.editor.leetcode._557_reverseWords;

class Solution {
    public String reverseWords(String s) {
        String[] temp = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        int size = temp.length;
        for (int i = 0; i < size; i++) {
            String word = temp[i];
            for (int j = word.length() - 1; j >= 0; j--) {
                stringBuilder.append(word.charAt(j));
            }
            stringBuilder.append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}