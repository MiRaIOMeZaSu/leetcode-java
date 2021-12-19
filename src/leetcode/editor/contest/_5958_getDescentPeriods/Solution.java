package leetcode.editor.contest._5958_getDescentPeriods;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public long getDescentPeriods(int[] prices) {
        int size = prices.length;
        long ans = size;
        int lastIndex = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < size; i++) {
            if (prices[i - 1] - prices[i] != 1) {
                if (i - lastIndex > 1) {
                    list.add(i - lastIndex);
                }
                lastIndex = i;
            }
        }
        if (lastIndex != size - 1) {
            list.add(size - lastIndex);
        }
        for (int i = 0; i < list.size(); i++) {
            ans += solve(list.get(i));
        }
        return ans;
    }

    private long solve(int length) {
        long a = length;
        long b = length - 1;
        if (a % 2 == 0) {
            a /= 2;
        } else {
            b /= 2;
        }
        return a * b;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getDescentPeriods(new int[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 4, 3, 10, 9, 8, 7}));
    }
}