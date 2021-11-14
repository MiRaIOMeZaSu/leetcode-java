package leetcode.editor.contest._5928_decodeCiphertext;

class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        // 不需要构造,使用下标即可
        int len = encodedText.length();
        int cols = len / rows;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cols; i++) {
            int index = i;
            for (int j = 0; j < rows && index < len; j++) {
                stringBuilder.append(encodedText.charAt(index));
                index += cols + 1;
            }
        }
        return stringBuilder.toString().stripTrailing();
    }
}