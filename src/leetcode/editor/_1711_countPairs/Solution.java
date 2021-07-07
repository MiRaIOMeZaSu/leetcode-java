package leetcode.editor._1711_countPairs;

import java.util.*;

class Solution {
    private static final int div = 1000000007;

    public int countPairs(int[] deliciousness) {
        // 恰好包含两道,不能多也不能少
        int size = deliciousness.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int limit = 0;
        for (int i = 0; i < size; i++) {
            int num = deliciousness[i];
            if (!map.containsKey(num)) {
                limit = Math.max(limit, num);
                list.add(num);
                map.put(num, 0);
            }
            map.put(num, map.get(num) + 1);
        }
        limit *= 2;
        size = list.size();
        int[] bit = new int[22];
        bit[0] = 1;
        for (int i = 1; i < 22; i++) {
            bit[i] = bit[i - 1] * 2;
        }
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        for (int i = 0; i < size; i++) {
            int num = list.get(i);
            int count = map.get(num);
            long localRes;
            for (int j = 0; j < 22; j++) {
                int target = bit[j];
                if (target > limit) {
                    break;
                }
                int a = target - num;
                if (a == num) {
                    localRes = ((long)count * (long)(count - 1)) / 2;
                    localRes = localRes % div;
                    res += localRes;
                } else if (a < 0 || visited.contains(a)) {
                    continue;
                } else {
                    localRes = map.getOrDefault(a, 0) * count;
                    localRes = localRes % div;
                    res += localRes;
                }
                res = res % div;
            }
            visited.add(num);
        }
        res = res % div;
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.countPairs(new int[]{1, 1, 1, 3, 3, 3, 7});
    }
}