package leetcode.editor._752_openLock;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

class Solution {
    int[] dp = new int[10000];
    int pivot;

    public int openLock(String[] deadends, String target) {
        // 动态规划
        if ("0000".equals(target)) {
            return 0;
        }
        for (int i = 0; i < deadends.length; i++) {
            if (target.equals(deadends[i])) {
                return -1;
            }
            if ("0000".equals(deadends[i])) {
                return -1;
            }
            dp[Integer.parseInt(deadends[i])] = -1;
        }
        pivot = Integer.parseInt(target);
        List<Integer> next = getNum(pivot);
        for (int i = 0; i < next.size(); i++) {
            solve(next.get(i), 1);
        }
        return dp[0] != 0 ? dp[0] : -1;
    }

    private void solve(int curr, int num) {
        if (curr == pivot) {
            return;
        }
        if (dp[curr] == -1) {
            return;
        }
        if (dp[0] > 0 && num >= dp[0]) {
            return;
        }
        if (dp[curr] == 0 || num < dp[curr]) {
            dp[curr] = num;
            // 往四边走
            List<Integer> next = getNum(curr);
            for (int i = 0; i < next.size(); i++) {
                solve(next.get(i), num + 1);
            }
        }
    }

    private List<Integer> getNum(int num) {
        List<Integer> ret = new ArrayList<>();
        int toPlus = 1;
        int curr = num;
        for (int i = 0; i < 4; i++) {
            int bit = curr % 10;
            if (bit == 9) {
                ret.add(num - toPlus * 9);
            } else {
                ret.add(num + toPlus);
            }
            if (bit == 0) {
                ret.add(num + toPlus * 9);
            } else {
                ret.add(num - toPlus);
            }
            toPlus *= 10;
            curr /= 10;
        }
        ret.sort(Comparator.naturalOrder());
        return ret;
    }
}