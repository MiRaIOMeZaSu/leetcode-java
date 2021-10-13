package leetcode.editor._5863_countQuadruplets;

import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    int size;
    int result = 0;
    int[] nums;

    public int countQuadruplets(int[] nums) {
        this.nums = nums;
        size = nums.length;
        solve(0, 0, 0);
        return result;
    }

    private void solve(int sum, int index, int count) {
        if (count == 3) {
            for (int i = index; i < size; i++) {
                if (sum == nums[i]) {
                    result++;
                }
            }
            return;
        }
        for (int i = index; i < size; i++) {
            solve(sum + nums[i], i + 1, count + 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.countQuadruplets(new int[]{1, 1, 1, 3, 5});
    }
}