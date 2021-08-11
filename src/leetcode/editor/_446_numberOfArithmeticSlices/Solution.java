package leetcode.editor._446_numberOfArithmeticSlices;

import java.util.*;

class Solution {
    private Map<Long, int[]>[] visited;
    private long[] myNums;
    private int size;
    private int result = 0;

    public int numberOfArithmeticSlices(int[] nums) {
        // 用邻接表存储已经计算过的
        // 某个区间内相同的数字如何处理? - 使用下标存储情况
        // 使用动态规划?存储到后面的长度!
        size = nums.length;
        myNums = new long[size];
        for (int i = 0; i < size; i++) {
            myNums[i] = nums[i];
        }
        if (size < 3) {
            return 0;
        } else if (size == 3) {
            return myNums[2] - myNums[1] == myNums[1] - myNums[0] ? 1 : 0;
        }

        visited = new HashMap[size];

        for (int i = 0; i < size - 2; i++) {
            for (int j = i + 1; j < size - 1; j++) {
                // 接下来往后找合适的
                if (visited[i] == null) {
                    visited[i] = new HashMap<>();
                }
                long gap = myNums[j] - myNums[i];
                solve(j, gap);
                result += visited[j].get(gap)[2] + visited[j].get(gap)[1];
                // 已经存在的直接跳过
            }
        }
        return result;
    }

    private void solve(int j, long gap) {
        if (visited[j] == null) {
            visited[j] = new HashMap<>();
        } else if (visited[j].containsKey(gap)) {
            return;
        }
        long target = myNums[j] + gap;
        int[] arr = new int[]{1, 0, 0};

        for (int i = j + 1; i < size; i++) {
            if (myNums[i] == target) {
                solve(i, gap);
                int[] list = visited[i].get(gap);
                arr[1] += 1;
                arr[2] += list[1] + list[2];
            }
        }
        visited[j].put(gap, arr);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        long ret = solution.numberOfArithmeticSlices(new int[]{
                2147483638, 2147483639, 2147483640, 2147483641, 2147483642, 2147483643,
                2147483644, 2147483645, 2147483646, 2147483647, -2147483648, -2147483647,
                -2147483646, -2147483645, -2147483644, -2147483643, -2147483642, -2147483641,
                -2147483640, -2147483639});
        System.out.println(ret);
    }
}