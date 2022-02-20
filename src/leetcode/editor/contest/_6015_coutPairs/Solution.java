package leetcode.editor.contest._6015_coutPairs;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public long coutPairs(int[] nums, int k) {
        // 遍历超时
        // 分解k?
        // 求的是数目
        int ans = 0;
        int currK = k;
        int size = nums.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 2; i <= k && currK != 1; i++) {
            while (currK % i == 0) {
                map.merge(i, 1, Integer::sum);
                currK /= i;
            }
        }
        if (map.size() == 0) {
            // size - 1 + size - 2 + ... + 1
            return size * (size - 1) / 2;
        }
        List<Integer> factors = new ArrayList<>(map.keySet());
        int factorSize = factors.size();
        HashMap<Integer, List<int[]>> listHashMap = new HashMap<>();
        for (int i = 0; i < factorSize; i++) {
            listHashMap.put(factors.get(i), new ArrayList<>());
        }
        // 统计了因子
        HashMap<Integer, Integer>[] maps = new HashMap[size];
        for (int i = 0; i < size; i++) {
            // 统计因子数量
            maps[i] = new HashMap<>();
            int currNum = nums[i];
            for (int j = 0; j < factorSize; j++) {
                int factor = factors.get(j);
                while (currNum % factor == 0) {
                    currNum /= factor;
                    maps[i].merge(factor, 1, Integer::sum);
                }
                listHashMap.get(factor).add(new int[]{nums[i], maps[i].getOrDefault(factor, 0), i});
            }
        }
        for (int i = 0; i < factorSize; i++) {
            listHashMap.get(factors.get(i)).sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o2[1] - o1[1];
                }
            });
        }
        for (int i = 0; i < size; i++) {
            // 开始过滤
            // 如何处理重复的数字?
            int num = nums[i];
            HashMap<Integer, Integer> factorMap = maps[i];
            HashSet<Integer> satisfy = new HashSet<>();
            List<int[]> others = listHashMap.get(factors.get(0));
            for (int l = 0; l < others.size(); l++) {
                if (factorMap.getOrDefault(factors.get(0), 0) + others.get(l)[1] >= map.getOrDefault(factors.get(0), 0)) {
                    if (others.get(l)[2] > i) {
                        satisfy.add(others.get(l)[2]);
                    }
                }
            }
            for (int j = 1; j < factorSize; j++) {
                others = listHashMap.get(factors.get(j));
                for (int l = 0; l < others.size(); l++) {
                    if (factorMap.getOrDefault(factors.get(j), 0) + others.get(l)[1] < map.getOrDefault(factors.get(j), 0)) {
                        satisfy.remove(others.get(l)[2]);
                    }
                }
            }
            ans += satisfy.size();
        }
        // 开始比较?
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.coutPairs(new int[]{1, 2, 3, 4, 5}, 2));
        System.out.println(solution.coutPairs(new int[]{3, 2, 6, 1, 8, 4, 1}, 3));
        System.out.println(solution.coutPairs(new int[]{8, 10, 2, 5, 9, 6, 3, 8, 2}, 6));
        System.out.println(solution.coutPairs(new int[]{5, 8}, 1));
    }
}