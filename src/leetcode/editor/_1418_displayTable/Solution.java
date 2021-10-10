package leetcode.editor._1418_displayTable;

import java.util.*;

class Solution {
//    public List<List<String>> displayTable(List<List<String>> orders) {
//        Map<String, Map<Integer, Integer>> map = new HashMap<>();
//        List<String> dishes = new ArrayList<>();
//        List<Integer> tables = new ArrayList<>();
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < orders.size(); i++) {
//            String dish = orders.get(i).get(2);
//            if (!map.containsKey(dish)) {
//                map.put(dish, new HashMap<>());
//                dishes.add(dish);
//            }
//            int tableNum = Integer.parseInt(orders.get(i).get(1));
//            if (!setprivate String getKey(String string) {ableNum)) {
//                set.add(tableNum);
//                tables.add(tableNum);
//            }
//            map.get(dish).put(tableNum, map.get(dish).getOrDefault(tableNum, 0) + 1);
//        }
//        dishes.sort(Comparator.naturalOrder());
//        tables.sort(Comparator.naturalOrder());
//        dishes.add(0, "Table");
//        List<List<String>> res = new ArrayList<>();
//        res.add(dishes);
//        for (int i = 0; i < tables.size(); i++) {
//            List<String> temp = new ArrayList<>();
//            int tableNum = tables.get(i);
//            temp.add(String.valueOf(tables.get(i)));
//            for (int j = 1; j < dishes.size(); j++) {
//                temp.add(String.valueOf(map.get(dishes.get(j)).getOrDefault(tableNum,0)));
//            }
//            res.add(temp);
//        }
//        return res;
//    }
}