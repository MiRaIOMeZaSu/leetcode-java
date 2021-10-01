package leetcode.editor._1713_minOperations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int minOperations(int[] target, int[] arr) {
        // 寻找最长公共子序列
        // 直接寻找最长公共子序列
        int longSize, shortSize;
        int[] longWord, shortWord;
        int len1 = target.length;
        int len2 = arr.length;
        boolean flag = len1 > len2;
        longSize = flag ? len1 : len2;
        shortSize = flag ? len2 : len1;
        longWord = flag ? target : arr;
        shortWord = flag ? arr : target;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < longSize; i++) {
            int index = longWord[i];
            if (!map.containsKey(index)) {
                map.put(index, i);
                continue;
            }
            map.put(index, Math.min(i, map.get(index)));
        }
        int ans = 0;
        int[][] dp = new int[shortSize][longSize];
        Set<Integer> curr = new HashSet<>();
        int index = shortWord[0];
        curr.add(index);
        if (map.containsKey(index)) {
            for (int i = map.get(index); i < longSize; i++) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < shortSize; i++) {
            int longIndex = longWord[0];
            int shortIndex = shortWord[i];
            curr.add(shortIndex);
            if (curr.contains(longIndex)) {
                dp[i][0] = 1;
            }
            for (int j = 1; j < longSize; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                dp[i][j] = Math.max(dp[i][j - 1], dp[i][j]);
                if (longWord[j] == shortWord[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
                }
            }
        }
        return target.length - dp[shortSize - 1][longSize - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minOperations(new int[]{
                5, 1, 3
        }, new int[]{
                9, 4, 2, 3, 4
        }));
    }
}