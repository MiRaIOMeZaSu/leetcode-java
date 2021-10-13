package leetcode.editor._313_nthSuperUglyNumber;

import java.util.*;

class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        // 最小堆
        // 动态规划?
        int size = primes.length;
        int[] result = new int[n + 1];
        result[1] = 1;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            set.add(primes[i]);
        }
        for (int i = 2; i <= n; i++) {
            int temp = set.pollFirst();
            result[i] = temp;
            for (int j = 0; j < size; j++) {
                int next = temp * primes[j];
                if (Integer.MAX_VALUE / temp < primes[j]) {
                    continue;
                }
                set.add(next);
            }
        }
        return result[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19});
    }
}