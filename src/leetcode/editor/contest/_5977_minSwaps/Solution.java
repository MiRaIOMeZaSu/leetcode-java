package leetcode.editor.contest._5977_minSwaps;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minSwaps(int[] nums) {
        // 如何保证
        // 使用前缀法
        int size = nums.length;
        int[] prex = new int[size + 1];
        List<Integer> list = new ArrayList<>(nums.length / 2);
        for (int i = 1; i <= size; i++) {
            prex[i] = prex[i - 1];
            if (nums[i - 1] == 1) {
                prex[i]++;
                list.add(i - 1);
            }
        }
        int count = prex[size];
        if(count==0){
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            // 需要计算的初始下标
            int startIndex = list.get(i);
            int endIndex = startIndex + count - 1;
            if (endIndex < size) {
                ans = Math.min(ans, count - (prex[endIndex + 1] - prex[startIndex]));
            } else {
                int curr = prex[size] - prex[startIndex];
                curr += prex[endIndex - size + 1];
                ans = Math.min(ans, count - curr);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minSwaps(new int[]{1,1,0,0,1});
    }
}