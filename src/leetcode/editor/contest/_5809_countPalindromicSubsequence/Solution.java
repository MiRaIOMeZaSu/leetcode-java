package leetcode.editor.contest._5809_countPalindromicSubsequence;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int countPalindromicSubsequence(String s) {
        // 只要两边相同即可
        int size = s.length();
        int[] count = new int[26];
        char[] chars = new char[26];
        for (int i = 0; i < 26; i++) {
            chars[i] = (char) ('a' + i);
        }
        for (int i = size - 1; i > 0; i--) {
            count[s.charAt(i) - 'a']++;
        }
        Set<String> set = new HashSet<>();
        int[] prefix = new int[26];
        prefix[s.charAt(0) - 'a'] = 1;
        for (int i = 1; i < size - 1; i++) {
            int key = s.charAt(i) - 'a';
            count[key]--;
            for (int j = 0; j < 26; j++) {
                if (count[j] * prefix[j] != 0) {
                    set.add(new String(new char[]{chars[j], chars[key], chars[j]}));
                }
            }
            prefix[key]++;
        }
        return set.size();
    }

    private void mapMinus(Map<Integer, Integer> map, int key) {
        int num = map.get(key);
        if (num == 1) {
            map.remove(key);
        } else {
            map.put(key, num - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.countPalindromicSubsequence("bbcbaba");
    }
}