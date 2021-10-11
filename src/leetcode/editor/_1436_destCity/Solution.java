package leetcode.editor._1436_destCity;

import java.util.*;

class Solution {
    public String destCity(List<List<String>> paths) {
        List<String> list = new ArrayList<>();
        Map<String, boolean[]> map = new HashMap<>();
        for (int i = 0; i < paths.size(); i++) {
            String start = paths.get(i).get(0);
            String end = paths.get(i).get(1);
            if (!map.containsKey(start)) {
                map.put(start, new boolean[2]);
                list.add(start);
            }
            if (!map.containsKey(end)) {
                map.put(end, new boolean[2]);
                list.add(end);
            }
            map.get(start)[0] = true;
            map.get(end)[1] = true;
        }
        for (int i = 0; i < list.size(); i++) {
            boolean[] booleans = map.get(list.get(i));
            if (!booleans[0] && booleans[1]) {
                return list.get(i);
            }
        }
        return paths.get(paths.size() - 1).get(1);
    }
}