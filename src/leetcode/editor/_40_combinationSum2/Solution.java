package leetcode.editor._40_combinationSum2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    List<List<Integer>> ret;
    int length;
    int target;
    int[] nums;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 排序+指针
        this.target = target;
        nums = candidates;
        ret = new ArrayList<>();
        Arrays.sort(candidates);
        length = candidates.length;
        // 移除多余的部分
        for (int i = length - 1; i >= 0; i--) {
            if (candidates[i] > target) {
                length--;
            }
        }
        // 开始遍历
        List<Integer> ans = new ArrayList<>();
        solve(new ArrayList<>(ans), 0, 0);
        return ret;
    }

    public void solve(List<Integer> ans, int sum, int index) {
        if (index >= length) {
            return;
        }
        int pre = 0;
        for (int i = index; i < length; i++) {
            if (nums[i] == pre) {
                continue;
            }
            pre = nums[i];
            sum += nums[i];
            ans.add(nums[i]);
            if (sum > target) {
                ans.remove(ans.size() - 1);
                return;
            } else if (sum == target) {
                ret.add(new ArrayList<>(ans));
                ans.remove(ans.size() - 1);
                return;
            }
            solve(ans, sum, i + 1);
            ans.remove(ans.size() - 1);
            sum -= nums[i];
        }
    }
}