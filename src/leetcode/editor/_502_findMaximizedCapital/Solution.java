package leetcode.editor._502_findMaximizedCapital;

import java.util.*;

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // 资产为多少时选择了多少个项目时将获得多少利润
        // 进行排序
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int size = capital.length;
        for (int i = 0; i < size; i++) {
            if (!map.containsKey(capital[i])) {
                map.put(capital[i], new ArrayList<>());
                list.add(capital[i]);
            }
            map.get(capital[i]).add(profits[i]);
        }
        list.sort(Comparator.naturalOrder());
        if (list.get(0) > w) {
            return 0;
        }
        // 背包法
        int result = 0;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(0, w);
        for (int i = 0; i < list.size(); i++) {
            int key = list.get(i);
            int len = map.get(key).size();
            map.get(key).sort(Comparator.reverseOrder());
            List<Integer> temp = map.get(key);
            for (int j = 0; j < len; j++) {
                if (j > 0) {
                    temp.set(j, temp.get(j - 1) + temp.get(j));
                }
                Iterator<Map.Entry<Integer, Integer>> it = treeMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, Integer> entry = it.next();
                    int nextK = entry.getKey() + j + 1;
                    if (nextK > k) {
                        break;
                    }
                    if (entry.getValue() < key) {
                        continue;
                    }
                    Integer val = treeMap.get(nextK);
                    int nextVal = entry.getValue() + temp.get(j);
                    result = Math.max(result, nextVal);
                    if (val == null || val < nextVal) {
                        treeMap.put(nextK, nextVal);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findMaximizedCapital(2, 2, new int[]{1, 2, 3}, new int[]{0, 1, 1});
    }
}