package leetcode.editor._1713_minOperations;

import com.sun.source.tree.Tree;

import java.util.*;

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
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arrSize; i++) {
            if (map.containsKey(arr[i])) {
                list.add(arr[i]);
            }
        }
        arrSize = list.size();
        if (arrSize == 0) {
            return targetSize;
        }
        for (int i = 0; i < arrSize; i++) {
            arr[i] = list.get(i);
        }
        int[] dp = new int[Math.min(arrSize, targetSize) + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        int ans = 1;
        dp[1] = map.get(arr[0]);
        for (int i = 1; i < arrSize; i++) {
            int currIndex = map.get(arr[i]);
            for (int j = ans; j >= 0; j--) {
                if (dp[j] <= currIndex) {
                    if (dp[j] < currIndex) {
                        dp[j + 1] = Math.min(dp[j + 1], currIndex);
                        ans = Math.max(j + 1, ans);
                        break;
                    }
                }
            }
            dp[1] = Math.min(dp[1], currIndex);
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