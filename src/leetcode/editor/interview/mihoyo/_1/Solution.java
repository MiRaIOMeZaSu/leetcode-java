package leetcode.editor.interview.mihoyo._1;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums int整型一维数组
     * @param k    int整型
     * @return int整型
     */
    public int findKthLargest(int[] nums, int k) {
        // write code here
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findKthLargest(new int[]{1,2,3,4},2));
    }
}