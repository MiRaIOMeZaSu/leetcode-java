package leetcode.editor._16_threeSumClosest;

import java.util.Map;
import java.util.TreeMap;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 1000个数
        // 使用TreeMap
        int size = nums.length;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            treeMap.merge(nums[i], 1, Integer::sum);
        }
        int pivot = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int toFind = target - nums[i] - nums[j];
                int currToFind = toFind;
                for (int k = 0; k < 2; k++) {
                    while (true) {
                        // 往左找
                        Map.Entry<Integer, Integer> entry;
                        if (k == 0) {
                            entry = treeMap.floorEntry(currToFind);
                        } else {
                            entry = treeMap.ceilingEntry(currToFind);
                        }
                        if (entry == null) {
                            break;
                        }
                        int localAns = Math.abs(entry.getKey() - toFind);
                        if (localAns > pivot) {
                            break;
                        }
                        int used = 1;
                        if (nums[i] == entry.getKey()) {
                            used++;
                        }
                        if (nums[j] == entry.getKey()) {
                            used++;
                        }
                        if (used > entry.getValue()) {
                            if (k == 0) {
                                currToFind = entry.getKey() - 1;
                            } else {
                                currToFind = entry.getKey() + 1;
                            }
                        } else {
                            pivot = localAns;
                            ans = nums[i] + nums[j] + entry.getKey();
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }
}
