package leetcode.editor._5839_minStoneSum;

import java.util.*;

class Solution {
    public int minStoneSum(int[] piles, int k) {
        // 移出再放入
        int size = piles.length;
        int total = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < size; i++) {
            total += piles[i];
            map.merge(piles[i], 1, Integer::sum);
        }
        while (k > 0) {
            Map.Entry<Integer, Integer> entry = map.firstEntry();
            int pivot = entry.getKey();
            int after = pivot - (pivot >> 1);
            if (k > entry.getValue()) {
                k -= entry.getValue();
                total -= (pivot - after) * entry.getValue();
                map.pollFirstEntry();
                map.merge(after, entry.getValue(), Integer::sum);
            } else {
                // 直接可以return了
                return total - (pivot - after) * k;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minStoneSum(new int[]{5, 4, 9}, 2);
    }
}