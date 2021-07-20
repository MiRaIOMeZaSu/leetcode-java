package leetcode.editor._1877_minPairSum;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

class Solution {
    int[] nums;
    int size;

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int size = nums.length;
        this.size = size;
        this.nums = nums;
        int total = 0;
        for (int i = 0; i < size; i++) {
            total += nums[i];
        }
        int start = Math.max(total / size + total % size == 0 ? 0 : 1, nums[size - 1] + nums[0]);
        int end = nums[size - 1] + nums[size - 2];
        int left = start;
        int right = end;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (statisfy(mid)) {
                // 满足
                right = mid;
            } else {
                // 不满足
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean statisfy(int target) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            map.merge(nums[i], 1, Integer::sum);
        }
        while (!map.isEmpty()) {
            Map.Entry<Integer, Integer> entry = map.firstEntry();
            int limit = target - entry.getKey() + 1;
            Map.Entry<Integer, Integer> temp = map.lowerEntry(limit);
            if (temp == null) {
                return false;
            }
            if (entry.getValue() > temp.getValue()) {
                map.put(entry.getKey(), entry.getValue() - temp.getValue());
//                entry.setValue(entry.getValue() - temp.getValue());
                map.remove(temp.getKey());
            } else if (entry.getValue() < temp.getValue()) {
                map.put(temp.getKey(), temp.getValue() - entry.getValue());
//                temp.setValue(temp.getValue() - entry.getValue());
                map.remove(entry.getKey());
            } else {
                map.remove(entry.getKey());
                map.remove(temp.getKey());
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.minPairSum(new int[]{3, 5, 4, 2, 4, 6, 3, 5, 2, 3, 3, 5, 2, 3, 4, 2, 4, 6, 3, 52, 321, 451, 23, 567, 12, 5, 71, 24, 761, 2, 56, 6111, 2, 5, 6, 12, 4, 6, 8, 12, 6, 2, 6, 5, 1, 33, 6, 8, 45, 8, 6, 45, 37, 8435, 23, 67, 3});
        System.out.println(result);
    }
}