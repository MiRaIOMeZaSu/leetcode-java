package leetcode.editor._76_minWindow;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        // 分析t得来的需求
        Map<String, Integer> needs = new HashMap<>();
        // 当前窗口中的字母统计
        Map<String, Integer> windows = new HashMap<>();
        // 初始化needs
        for (int i = 0; i < t.length(); i++) {
            String need = String.valueOf(t.charAt(i));
            needs.put(need, needs.getOrDefault(need, 0) + 1);
            windows.put(need, 0);
        }
        int left = 0;
        int right = 0;
        String ch;
        String minString = s;
        while (right < s.length()) {
            ch = String.valueOf(s.charAt(right));
            if (needs.containsKey(ch)) {
                windows.put(ch, windows.getOrDefault(ch, 0) + 1);
            }
            // 左指针往后走
            while (isValid(needs, windows)) {
                // 在此处进行结果的比较和存储
                if (minString.length() > right - left + 1) {
                    minString = s.substring(left, right + 1);
                }
                ch = String.valueOf(s.charAt(left));
                if (needs.containsKey(ch)) {
                    windows.put(ch, windows.get(ch) - 1);
                }
                left++;
            }
            right++;
        }
        if (!isValid(needs, windows) && left == 0 && right == s.length()) {
            return "";
        }
        return minString;
    }

    public boolean isValid(Map<String, Integer> needs, Map<String, Integer> windows) {
        // 判断是否满足需求
        for (Map.Entry<String, Integer> entry : needs.entrySet()) {
            if (needs.get(entry.getKey()) > windows.get(entry.getKey())) {
                return false;
            }
        }
        return true;
    }
}