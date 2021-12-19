package leetcode.editor.contest._5957_addSpaces;

class Solution {
    public String addSpaces(String s, int[] spaces) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = s.length();
        int spaceLen = spaces.length;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (index < spaceLen && i == spaces[index]) {
                stringBuilder.append(' ');
                index++;
            }
            stringBuilder.append(s.charAt(i));
        }
        return stringBuilder.toString();
    }
}