package leetcode.editor._446_numberOfArithmeticSlices;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        // 用邻接表存储已经计算过的
        // 某个区间内相同的数字如何处理? - 使用下标存储情况
        int size = nums.length;
        Set<Integer>[] visited = new HashSet[size];
        int result = 0;
        for (int i = 0; i < size - 2; i++) {
            for (int j = i + 1; j < size - 1; j++) {
                // 接下来往后找合适的
                if (!visited[i].contains(j)) {
                    int gap = nums[j] - nums[i];
                    int start = nums[j] + gap;
                    int lastIndex = j;
                    int length = 2;
                    for (int x = j + 1; x < size; x++) {
                        if (nums[x] == start) {
                            start += gap;
                            visited[lastIndex].add(x);
                            lastIndex = x;
                            length++;
                        }
                    }
                    result += times(length);
                }
                // 已经存在的直接跳过
            }
        }
        return result;
    }

    private int times(int num) {
        if (num == 3) {
            return 1;
        }
        int temp = num - 2;
        return temp * (temp + 1) / 2;
    }
}