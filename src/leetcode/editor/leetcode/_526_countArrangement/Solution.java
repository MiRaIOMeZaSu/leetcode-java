package leetcode.editor._526_countArrangement;

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<Integer>[] lists;
    int[] bits = new int[17];
    int n;
    int result = 0;

    public int countArrangement(int n) {
        this.n = n;
        bits[1] = 1;
        for (int i = 2; i <= n + 1; i++) {
            bits[i] = bits[i - 1] << 1;
        }
        // 提前将每个位置的可放置数计算出来
        lists = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    lists[i].add(j);
                }
            }
        }
        solve(0, 1);
        return result;
    }

    private void solve(int bit, int index) {
        if (bit == bits[n + 1] - 1) {
            result += 1;
            return;
        }
        List<Integer> list = lists[index];
        for (int i = 0; i < list.size(); i++) {
            int nextBit = bit | bits[list.get(i)];
            if (nextBit != bit) {
                solve(nextBit, index + 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.countArrangement(15);
        System.out.println(ret);
    }
}