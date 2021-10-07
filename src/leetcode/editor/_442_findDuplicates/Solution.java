package leetcode.editor._442_findDuplicates;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        // 找出所有
        // 只有出现一次的和出现两次的
        int size = nums.length;
        int index = 0;
        List<Integer> ans = new ArrayList<>();
        while (index < size) {
            if (nums[index] == -1) {
                index++;
                continue;
            }
            int targetIndex = nums[index] - 1;
            if (targetIndex != index && nums[targetIndex] == nums[index]) {
                ans.add(nums[index]);
                nums[index] = -1;
                nums[targetIndex] = -1;
                index++;
            } else if (targetIndex == index) {
                index++;
            }else {
                exchange(nums, targetIndex, index);
            }
        }
        return ans;
    }

    private void exchange(int[] nums, int indexA, int indexB) {
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}