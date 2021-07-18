package leetcode.editor.interview._1002_groupAnagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        int size = strs.length;
        for (int i = 0; i < size; i++) {
            String key = getKey(strs[i]);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }
        for (List<String> list : map.values()) {
            result.add(list);
        }
        return result;
    }

    private String getKey(String string) {
        int[] letters = new int[26];
        int size = string.length();
        for (int i = 0; i < size; i++) {
            letters[string.charAt(i) - 'a']++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            stringBuilder.append(letters[i]);
            stringBuilder.append("|");
        }
        return stringBuilder.toString();
    }
}