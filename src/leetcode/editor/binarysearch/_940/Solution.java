package leetcode.editor.binarysearch._940;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solve(String s, String t) {
        int ret = 0;
        // directly compute res when gap equals zero
        Map<Character, Integer> table = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            table.put(t.charAt(i), table.getOrDefault(t.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            ret += t.length() - table.getOrDefault(s.charAt(i), 0);
        }
        for (int gap = 1; gap < Math.min(s.length(), t.length()); gap++) {
            int left = 0;
            int right = left + gap;
            while (right < s.length()) {
                int index = 0;
                while (index + gap < t.length()) {
                    // start to compare
                    int diffCount = 0;
                    for (int i = 0; i <= gap; i++) {
                        if (s.charAt(left + i) != t.charAt(index + i)) {
                            diffCount++;
                            if (diffCount > 1) {
                                break;
                            }
                        }
                    }
                    if (diffCount == 1) {
                        ret++;
                    }
                    index++;
                }
                left++;
                right++;
            }
        }
        return ret;
    }
}