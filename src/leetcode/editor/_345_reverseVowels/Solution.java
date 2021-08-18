package leetcode.editor._345_reverseVowels;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public String reverseVowels(String s) {
        String vowelString = "aAoOuUiIeE";
        Set<Character> vowels = new HashSet<>();
        for (int i = 0; i < vowelString.length(); i++) {
            vowels.add(vowelString.charAt(i));
        }
        char[] chars = s.toCharArray();
        int size = s.length();
        int l = 0;
        int r = size - 1;
        for (; l < size; l++) {
            if (vowels.contains(chars[l])) {
                // 此时如何?
                break;
            }
        }
        for (; r >= 0; r--) {
            if (vowels.contains(chars[r])) {
                // 此时如何?
                break;
            }
        }
        while (l < r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
            for (; l < size; l++) {
                if (vowels.contains(chars[l])) {
                    // 此时如何?
                    break;
                }
            }
            for (; r >= 0; r--) {
                if (vowels.contains(chars[r])) {
                    // 此时如何?
                    break;
                }
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseVowels("hello"));
    }
}