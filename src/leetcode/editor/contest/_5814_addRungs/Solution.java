package leetcode.editor.contest._5814_addRungs;

import java.util.*;

class Solution {
    public int addRungs(int[] rungs, int dist) {
        int size = rungs.length;
        Map<Integer, Integer> hashMap = new HashMap();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            map.put(rungs[i], i);
            hashMap.put(rungs[i], i);
        }
        int[] dp = new int[size];
        dp[0] = rungs[0] / dist - 1 + (rungs[0] % dist == 0 ? 0 : 1);
        for (int i = 1; i < size; i++) {
            int temp = rungs[i] - rungs[i - 1];
            if (temp > dist) {
                dp[i] = dp[i - 1] + temp / dist - 1 + (temp % dist == 0 ? 0 : 1);
                continue;
            }
            int target = rungs[i] - dist;
            dp[i] = dp[i - 1];
            if (hashMap.containsKey(target)) {
                dp[i] = dp[hashMap.get(target)];
            }
            Map.Entry<Integer, Integer> entry = map.lowerEntry(rungs[i] - dist);
            if (entry != null) {
                int key = entry.getKey();
                int val = entry.getValue();
                temp = rungs[i] - key;
                int comp = dp[val] + temp / dist - 1 + (temp % dist == 0 ? 0 : 1);
                dp[i] = Math.min(comp, dp[i]);
            }
        }
        return dp[size - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.addRungs(new int[]{3,6,8,10}, 3);
    }
}