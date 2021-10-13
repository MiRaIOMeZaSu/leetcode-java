package leetcode.editor._77_combine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        int[] nums = new int[k];
        for (int i = 1; i <= n; i++) {
            nums[0] = i;
            backtrack(nums, n, k, results, 1);
        }
        return results;
    }

    public void backtrack(int[] nums, int n, int k, List<List<Integer>> results, int size) {
        if (size == k) {
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                result.add(nums[i]);
            }
            results.add(result);
            return;
        }
        for (int i = nums[size - 1] + 1; i <= n; i++) {
            nums[size] = i;
            backtrack(nums, n, k, results, size + 1);
        }
    }
}