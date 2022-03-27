package leetcode.editor.contest._5269_maxValueOfCoins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int totalCount = 0;
        int totalSum = 0;
        for (int i = 0; i < piles.size(); i++) {
            totalCount += piles.get(i).size();
            for (int j = 0; j < piles.get(i).size(); j++) {
                totalSum += piles.get(i).get(j);
            }
        }
        if (totalCount <= k) {
            return totalSum;
        }
        int[] restMax = new int[k + 1];
        restMax[k - 1] = piles.get(0).get(0);
        for (int i = 1; i < piles.get(0).size() && k - (i + 1) >= 0; i++) {
            restMax[k - (i + 1)] = restMax[k - (i + 1) + 1] + piles.get(0).get(i);
        }
        for (int i = 1; i < piles.size(); i++) {
            int[] nextMax = new int[k + 1];
            System.arraycopy(restMax, 0, nextMax, 0, k);
            // 求出前缀和
            int[] prex = new int[piles.get(i).size()];
            prex[0] = piles.get(i).get(0);
            for (int j = 1; j < piles.get(i).size(); j++) {
                prex[j] = prex[j - 1] + piles.get(i).get(j);
            }
            for (int j = 0; j < piles.get(i).size(); j++) {
                // j + 1: 要加入的数量
                for (int l = k; l - j - 1 >= 0 && l >= 0; l--) {
                    nextMax[l - j - 1] = Math.max(nextMax[l - j - 1], restMax[l] + prex[j]);
                }
            }
            restMax = nextMax;
        }
        return restMax[0];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        List<List<Integer>> list = new ArrayList<>();
//        list.add(Arrays.asList(1, 100, 3));
//        list.add(Arrays.asList(7, 8, 9));
//        System.out.println(solution.maxValueOfCoins(list, 2));
        List<List<Integer>> list1 = new ArrayList<>();
        list1.add(Arrays.asList(100));
        list1.add(Arrays.asList(100));
        list1.add(Arrays.asList(100));
        list1.add(Arrays.asList(100));
        list1.add(Arrays.asList(100));
        list1.add(Arrays.asList(100));
        list1.add(Arrays.asList(100));
        list1.add(Arrays.asList(1, 1, 1, 1, 1, 1, 700));
        System.out.println(solution.maxValueOfCoins(list1, 7));
    }
}