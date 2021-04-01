package leetcode.editor._39_combinationSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    int[] nums;
    int target;
    List<List<Integer>> ret;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.target = target;
        this.nums = candidates;
        this.ret = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int index = 0;
        int sum = 0;
        solve(new ArrayList<>(ans), index + 1, sum);
        while (sum < target) {
            sum += nums[index];
            ans.add(nums[index]);
            solve(new ArrayList<>(ans), index + 1, sum);
        }
        return ret;
    }

    public void solve(List<Integer> item, int index, int sum) {
        if (sum > target) {
            return;
        } else if (sum == target) {
            ret.add(new ArrayList<>(item));
            return;
        }
        if (index >= nums.length) {
            return;
        }
        solve(new ArrayList<>(item), index + 1, sum);
        while (sum < target) {
            sum += nums[index];
            item.add(nums[index]);
            solve(new ArrayList<>(item), index + 1, sum);
        }
    }
}