package leetcode.editor._1713_minOperations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int minOperations(int[] target, int[] arr) {
        // 一遍寻找?
        // 寻找最长公共子序列
        // 直接寻找最长公共子序列
        // 重点来了,target中的数字互不相同!
        int arrSize = arr.length;
        int targetSize = target.length;
        Map<Integer, int[]> map = new HashMap<>(targetSize);
        map.put(0, new int[]{0, 0});
        int last = 0;
        for (int i = 0; i < targetSize; i++) {
            map.put(target[i], new int[]{last, 0});
            last = target[i];
        }
        for (int i = 0; i < arrSize; i++) {
            if (map.containsKey(arr[i])) {
                int[] pres = map.get(arr[i]);
                int next = map.get(pres[0])[1] + 1;
                if (next > pres[1]) {
                    pres[1] = next;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < targetSize; i++) {
            ans = Math.max(ans, map.get(target[i])[1]);
        }
        return targetSize - ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minOperations(new int[]{
                5,1,3
        }, new int[]{
                9,4,2,3,4
        }));
    }
}