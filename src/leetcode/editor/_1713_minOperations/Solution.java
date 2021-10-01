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
        int[][] lastDp = new int[2][longSize];
        Set<Integer> curr = new HashSet<>();
        int index = shortWord[0];
        curr.add(index);
        if (map.containsKey(index)) {
            for (int i = map.get(index); i < longSize; i++) {
                lastDp[0][i] = 1;
            }
        }
        for (int i = 1; i < shortSize; i++) {
            int longIndex = longWord[0];
            int shortIndex = shortWord[i];
            curr.add(shortIndex);
            if (curr.contains(longIndex)) {
                lastDp[1][0] = 1;
            }
            for (int j = 1; j < longSize; j++) {
                lastDp[1][j] = Math.max(lastDp[0][j], lastDp[1][j]);
                lastDp[1][j] = Math.max(lastDp[1][j - 1], lastDp[1][j]);
                if (longWord[j] == shortWord[i]) {
                    lastDp[1][j] = Math.max(lastDp[0][j - 1] + 1, lastDp[1][j]);
                }
            }
            lastDp[0] = lastDp[1];
            lastDp[1] = new int[longSize];
        }
        return target.length - lastDp[0][longSize - 1];
    }

    private int max(int[] nums) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > ans) {
                ans = nums[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minOperations(new int[]{
                6,4,8,1,3,2
        }, new int[]{
                4,7,6,2,3,8,6,1
        }));
    }
}