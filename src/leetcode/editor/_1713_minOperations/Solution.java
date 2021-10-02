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
        Map<Integer, Integer> map = new HashMap<>(targetSize);
        for (int i = 0; i < targetSize; i++) {
            map.put(target[i], i);
        }
        int[] dp = new int[targetSize];
        for (int i = 0; i < arrSize; i++) {
            if (map.containsKey(arr[i])) {
                int index = map.get(arr[i]);
                int j = index - 1;
                if (index == 0) {
                    dp[index] = 1;
                }
                while (dp[index] < j + 2 && j >= 0) {
                    dp[index] = Math.max(dp[index], dp[j] + 1);
                    j--;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < targetSize; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return targetSize - ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minOperations(new int[]{
                11, 16, 20, 1, 2, 13, 7, 6, 12, 3
        }, new int[]{
                11, 13, 3, 7, 7, 1, 10, 12, 14, 1
        }));
    }
}