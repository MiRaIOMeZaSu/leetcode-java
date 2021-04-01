package leetcode.editor._283_moveZeroes;


import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public void moveZeroes(int[] nums) {
        // 存储0的下标
        Queue<Integer> indexs = new LinkedList<>();
        int countZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                indexs.add(i);
                countZero++;
            } else {
                if (!indexs.isEmpty()) {
                    int index = indexs.poll();
                    indexs.add(i);
                    nums[index] = nums[i];
                }
            }
        }
        for (int i = nums.length - countZero; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}