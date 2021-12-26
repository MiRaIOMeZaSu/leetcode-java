package leetcode.editor.contest._5965_getDistances;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public long[] getDistances(int[] arr) {
        // 注意long
        int size = arr.length;
        long[] ans = new long[size];
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int key = arr[i];
            if (!hashMap.containsKey(key)) {
                hashMap.put(key, new ArrayList<>());
            }
            hashMap.get(key).add(i);
        }
        for (Integer key :
                hashMap.keySet()) {
            List<Integer> list = hashMap.get(key);
            for (int i = 0; i < list.size(); i++) {
                long temp = 0;
                for (int j = 0; j < list.size(); j++) {
                    temp += Math.abs(list.get(i) - list.get(j));
                }
                ans[list.get(i)] = temp;
            }
        }
        return ans;
    }
}