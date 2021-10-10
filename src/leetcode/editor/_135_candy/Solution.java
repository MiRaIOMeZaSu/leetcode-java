package leetcode.editor._135_candy;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int candy(int[] ratings) {
        int size = ratings.length;
        if (size == 1) {
            return 1;
        }
        int[] nums = new int[size];
        List<Integer> list = new ArrayList<>();
        if (ratings[0] <= ratings[1]) {
            nums[0] = 1;
            list.add(0);
        }
        for (int i = 1; i < size - 1; i++) {
            if (ratings[i] <= ratings[i - 1] && ratings[i] <= ratings[i + 1]) {
                nums[i] = 1;
                list.add(i);
            }
        }
        if (ratings[size - 1] <= ratings[size - 2]) {
            nums[size - 1] = 1;
            list.add(size - 1);
        }
        // 往后走
        for (int i = 0; i < list.size(); i++) {
            int index = list.get(i);
            for (int j = index + 1; j < size && ratings[j] > ratings[j - 1]; j++) {
                nums[j] = Math.max(nums[j], nums[j - 1] + 1);
            }
        }
        // 往前走
        for (int i = list.size() - 1; i >= 0; i--) {
            int index = list.get(i);
            for (int j = index - 1; j >= 0 && ratings[j] > ratings[j + 1]; j--) {
                nums[j] = Math.max(nums[j], nums[j + 1] + 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < size; i++) {
            ans += nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.candy(new int[]{4, 5, 6, 4, 6, 6, 9, 10}));
    }
}