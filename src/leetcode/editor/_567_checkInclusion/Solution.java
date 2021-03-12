package leetcode.editor._567_checkInclusion;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // 尝试通过char数组减少字符串使用消耗
        char[] c1 = s2.toCharArray();
        char[] c2 = s1.toCharArray();
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char ch : c2) {
            needs.put(ch, needs.getOrDefault(ch, 0) + 1);
        }
        int left = 0;
        int right = 0;

        while (right < c1.length) {
            if (this.contain(c1[right], c2)) {
                window.put(c1[right], window.getOrDefault(c1[right], 0) + 1);
            }
            while (this.isValid(needs, window)) {
                if (this.isEqual(needs, window) && c2.length == right - left + 1) {
                    return true;
                }
                if (this.contain(c1[left], c2)) {
                    window.put(c1[left], window.get(c1[left]) - 1);
                }
                left++;
            }
            right++;
        }
        return false;
    }

    public boolean isValid(Map<Character, Integer> needs, Map<Character, Integer> window) {
        for (Character ch : needs.keySet()) {
            if (needs.get(ch) > window.getOrDefault(ch, 0)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEqual(Map<Character, Integer> needs, Map<Character, Integer> window) {
        for (Character ch : needs.keySet()) {
            if (!needs.get(ch).equals(window.getOrDefault(ch, 0))) {
                return false;
            }
        }
        return true;
    }

    public boolean contain(char ch, char[] chars) {
        for (char c : chars) {
            if (ch == c) {
                return true;
            }
        }
        return false;
    }
}