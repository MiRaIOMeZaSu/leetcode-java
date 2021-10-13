package leetcode.editor._5828_minSpaceWastedKResizing;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minSpaceWastedKResizing(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        List<Integer> countArr = new ArrayList<>();
        int size = nums.length;
        list.add(nums[0]);
        int count = 1;
        for (int i = 1; i < size; i++) {
            if (list.get(list.size() - 1) == nums[i]) {
                count++;
            } else {
                countArr.add(count);
                list.add(nums[i]);
                count = 1;
            }
        }
        countArr.add(count);
        size = list.size();
        int[][] dp = new int[size][k + 1];
        return 0;
    }
}