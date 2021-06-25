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
        List<int[]> next = getNum(pivot);
        for (int i = 0; i < next.size(); i++) {
            solve(next.get(i)[0], 1);
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
            List<int[]> next = getNum(curr);
            for (int i = 0; i < next.size(); i++) {
                solve(next.get(i)[0], num + 1);
            }
        }
    }

    private List<int[]> getNum(int num) {
        List<int[]> ret = new ArrayList<>();
        int bitCount = 0;
        int temp = num;
        for (int i = 0; i < 4; i++) {
            bitCount += temp % 10;
            temp /= 10;
        }
        int toPlus = 1;
        int curr = num;
        for (int i = 0; i < 4; i++) {
            int bit = curr % 10;
            int[] add = new int[2];
            if (bit == 9) {
                add[0] = num - toPlus * 9;
                add[1] = bitCount - 9;
            } else {
                add[0] = num + toPlus;
                add[1] = bitCount + 1;
            }
            ret.add(add);
            int[] minus = new int[2];
            if (bit == 0) {
                minus[0] = num + toPlus * 9;
                minus[1] = bitCount + 9;
            } else {
                minus[0] = num - toPlus;
                minus[1] = bitCount - 1;
            }
            ret.add(minus);
            toPlus *= 10;
            curr /= 10;
        }
        ret.sort((o1, o2) -> o1[1] - o2[1]);
        return ret;
    }
}