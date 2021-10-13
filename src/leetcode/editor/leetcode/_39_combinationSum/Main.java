package leetcode.editor._39_combinationSum;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{2,3,6,7};
        List<List<Integer>> ret =  solution.combinationSum(nums, 7);
        System.out.println(ret);
    }
}
