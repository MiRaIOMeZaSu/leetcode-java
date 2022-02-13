package leetcode.editor.contest._6007_maximumANDSum;

import com.sun.source.tree.Tree;

import java.util.*;

class Solution {
    public int maximumANDSum(int[] nums, int numSlots) {
        /**
         * n == nums.length
         * 1 <= numSlots <= 9
         * 1 <= n <= 2 * numSlots
         * 1 <= nums[i] <= 15
         */
        int ans = 0;
        // 选择的最好等于自身,或者比自己大
        // 有可能才刚刚够分

        // 1~9:0001 - 1001
        // 1~15:1111 - 0001
        // 从小的开始比较实惠
        int[] rest = new int[numSlots + 1];
        for (int i = 1; i < numSlots; i++) {
            rest[i] = 2;
        }
        int size = nums.length;
        Map<Integer, Integer> integerIntegerTreeMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            integerIntegerTreeMap.merge(nums[i], 1, Integer::sum);
        }
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        integerIntegerTreeMap.forEach(
                (k, v) -> {
                    int val = v;
                    if (k <= numSlots && rest[numSlots] > 0) {
                        if (val > rest[numSlots]) {
                            val -= rest[numSlots];
                            rest[numSlots] = 0;
                        } else {
                            rest[numSlots] -= val;
                            val = 0;
                        }
                    }
                    if (val > 0) {
                        treeMap.put(k, val);
                    }
                }
        );
        // 排除了固定的



        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.maximumANDSum(new int[]{1, 3, 10, 4, 7, 1}, 9);
    }
}