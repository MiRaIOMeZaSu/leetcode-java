package leetcode.editor._474_findMaxForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // 01背包
        // 还需要统计数量!
        // 要使子集最大
        // 添加一维?
        int ret = 0;
        HashMap<Integer, HashMap<Integer, Integer>> dp = new HashMap<>();
        HashMap<Integer, HashMap<Integer, Integer>> updateDp = new HashMap<>();
        HashMap<Integer, Integer> tempTable = new HashMap<>();
        tempTable.put(0, 0);
        dp.put(0, tempTable);
        // 初始化完毕
        for (int i = 0; i < strs.length; i++) {
            int numCount[] = getCount(strs[i]);
            for (int key : dp.keySet()) {
                for (int subKey : dp.get(key).keySet()) {
                    int lastCount = dp.get(key).get(subKey);
                    int targetKey = numCount[0] + key;
                    if (targetKey > m) {
                        break;
                    }
                    int targetSubKey = numCount[1] + subKey;
                    if (targetSubKey > n) {
                        continue;
                    }
                    // 开始插入
                    if (!updateDp.containsKey(targetKey)) {
                        updateDp.put(targetKey, new HashMap<>());
                    }
                    int tempCount = Math.max(lastCount + 1, updateDp.get(targetKey).getOrDefault(targetSubKey, lastCount + 1));
                    ret = Math.max(tempCount, ret);
                    updateDp.get(targetKey).put(targetSubKey, tempCount);
                }
            }
            for (int key : updateDp.keySet()) {
                for (int subKey : updateDp.get(key).keySet()) {
                    if (!dp.containsKey(key)) {
                        dp.put(key, new HashMap<>());
                    }
                    dp.get(key).put(subKey, updateDp.get(key).get(subKey));
                }
            }
        }
        return ret;
    }

    private int[] getCount(String s) {
        int ret[] = new int[2];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                ret[0]++;
            } else {
                ret[1]++;
            }
        }
        return ret;
    }
}