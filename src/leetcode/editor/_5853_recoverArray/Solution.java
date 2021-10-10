package leetcode.editor._5853_recoverArray;

import java.lang.reflect.Array;
import java.util.*;

class Solution {
    Integer[] ready;
    int n;
    int total = 0;
    Map<Integer, Integer> map;

    public int[] recoverArray(int n, int[] sums) {
        this.n = n;
        int size = sums.length;
        for (int i = 0; i < size; i++) {
            total += sums[i];
        }
        for (int i = 0; i < n - 1; i++) {
            total /= 2;
        }
        // 全正数和全负数的区分?
        // 一个数一个数地动态规划(回溯?
        // 要求从目标数组中选出n个数符合要求
        // 二分式的验证?
        map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.merge(sums[i], 1, Integer::sum);
        }
        ready = map.keySet().toArray(new Integer[0]);
        Arrays.sort(ready);
        // 在这之中选择n个数
        List<Integer> input = new ArrayList<>();
        solve(input, 0, 0);
        return new int[]{};
    }

    private int solve(List<Integer> list, int index, int sum) {
        if (sum >= total) {
            if (check(list)) {
                return 1;
            }
            return -1;
        }
        if (list.size() == n) {
            if (sum == total) {
                // 开始判断和
                return 0;
            } else {
                return sum < total ? -1 : 1;
            }
        }
        for(int i = index;i <ready.length&& i + (ready.length - list.size()) < ready.length;i++){

        }
        return 1;
    }

    private boolean check(List<Integer> list) {
        Map<Integer, Integer> otherMap = new HashMap<>();
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recoverArray(4, new int[]{0, 0, 5, 5, 4, -1, 4, 9, 9, -1, 4, 3, 4, 8, 3, 8});
    }
}