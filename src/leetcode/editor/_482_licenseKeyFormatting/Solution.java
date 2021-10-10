package leetcode.editor._482_licenseKeyFormatting;

class Solution {
    public String licenseKeyFormatting(String s, int k) {
        int size = s.length();
        char[] chars = s.toCharArray();
        int count = 0;
        char letter = '-';
        for (int i = 0; i < size; i++) {
            if (chars[i] == letter) {
                count++;
            }
        }
        int rest = (size - count) % k;
        // 段数
        int segmentCount = (size - count) / k + (rest > 0 ? 1 : 0);
        int resultLength = segmentCount - 1 + size - count;
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (stringBuilder.length() < rest) {
            if (chars[index] != letter) {
                stringBuilder.append(Character.toUpperCase(chars[index]));
            }
            index++;
        }
        if (rest < size - count && rest > 0) {
            stringBuilder.append(letter);
        }
        int counter = 0;
        for (int i = index; i < size; i++) {
            if (chars[i] != letter) {
                stringBuilder.append(Character.toUpperCase(chars[i]));
                counter++;
                if (counter == k) {
                    counter = 0;
                    if (stringBuilder.length() == resultLength) {
                        return stringBuilder.toString();
                    }
                    stringBuilder.append(letter);
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.licenseKeyFormatting("5F3Z-2e-9-w",
                4);
    }
}