package leetcode.editor._930_numSubarraysWithSum;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int size = nums.length;
        int[] prefix = new int[size];
        prefix[0] = nums[0];
        for (int i = 1; i < size; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
        // 由于为零一,因而目标最只会减少
        Map<Integer, Integer> map = new HashMap<>();
        int[] tail = new int[size];
        tail[size - 1] = nums[size - 1];
        map.put(nums[size - 1], 1);
        for (int i = size - 2; i > 0; i--) {
            tail[i] = tail[i + 1] + nums[i];
            map.put(tail[i], map.getOrDefault(tail[i], 0) + 1);
        }
        int res = 0;
        res += map.getOrDefault(goal, 0);
        res += map.getOrDefault(prefix[size - 1] - goal, 0);
        if (prefix[size - 1] == goal) {
            res += 1;
        }
        for (int i = 0; i < size - 1; i++) {
            int target = prefix[size - 1] - goal - prefix[i];
            int num = map.getOrDefault(tail[i + 1], 0);
            if (num == 1) {
                map.remove(tail[i + 1]);
            } else {
                map.put(tail[i + 1], num - 1);
            }
            res += map.getOrDefault(target, 0);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.numSubarraysWithSum(new int[]{0, 1, 1, 1, 1}, 3);
        System.out.println(res);
    }
}