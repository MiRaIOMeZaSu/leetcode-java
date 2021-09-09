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
        Map<Integer, Integer> kMap = new HashMap<>();
        TreeSet<Integer> Ks = new TreeSet<>(((o1, o2) -> (o2 - o1)));
        kMap.put(0, w);
        Ks.add(0);
        for (int i = 0; i < list.size(); i++) {
            int key = list.get(i);
            int len = map.get(key).size();
            map.get(key).sort(Comparator.reverseOrder());
            List<Integer> temp = map.get(key);
            Map<Integer, Integer> nextMap = new HashMap<>();
            List<Integer> nextKs = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                if (j > 0) {
                    temp.set(j, temp.get(j - 1) + temp.get(j));
                }
                // 上面部分过程中发生的重复,
                Iterator<Integer> it = Ks.iterator();
                while (it.hasNext()) {
                    int currK = it.next();
                    int currVal = kMap.get(currK);
                    int nextK = currK + j + 1;
                    if (nextK > k) {
                        continue;
                    }
                    if (currVal < key) {
                        continue;
                    }
                    Integer val = nextMap.get(nextK);
                    int nextVal = currVal + temp.get(j);
                    result = Math.max(result, nextVal);
                    if (val == null || val < nextVal) {
                        if (val == null) {
                            nextKs.add(nextK);
                        }
                        nextMap.put(nextK, nextVal);
                    }
                }
            }
            Ks.addAll(nextKs);
            for (int j = 0; j < nextKs.size(); j++) {
                int Kkey = nextKs.get(j);
                Integer pivot = kMap.get(Kkey);
                Integer newVal = nextMap.get(Kkey);
                if (pivot == null || pivot < newVal) {
                    kMap.put(Kkey, newVal);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}