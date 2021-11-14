package leetcode.editor.leetcode._216_combinationSum3;

// 找出所有相加之和为 n 的 k 个数的组合
// k: 2-9
// n: 1-60

import java.util.ArrayList;
import java.util.List;

class Solution {
    int k, n;
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k;
        this.n = n;
        solve(new ArrayList<>(), 0, 0, 1);
        return ans;
    }

    private void solve(List<Integer> list, int currSum, int count, int start) {
        if (count == k) {
            if (currSum == n) {
                ans.add(new ArrayList<>(list));
            }
            return;
        }
        if (currSum >= n) {
            return;
        }
        if (9 - start + 1 < k - count) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            list.add(i);
            solve(list, currSum + i, count + 1, i + 1);
            list.remove(count);
        }
    }
}