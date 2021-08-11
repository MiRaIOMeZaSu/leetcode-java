package leetcode.editor._446_numberOfArithmeticSlices;

import java.util.*;

class Solution {
    private Map<Integer, int[]>[] visited;
    private int[] nums;
    private int size;
    private int result = 0;

    public int numberOfArithmeticSlices(int[] nums) {
        // 用邻接表存储已经计算过的
        // 某个区间内相同的数字如何处理? - 使用下标存储情况
        // 使用动态规划?存储到后面的长度!
        size = nums.length;
        this.nums = nums;
        visited = new HashMap[size];
        for (int i = 0; i < size - 2; i++) {
            for (int j = i + 1; j < size - 1; j++) {
                // 接下来往后找合适的
                if (visited[i] == null) {
                    visited[i] = new HashMap<>();
                }
                int gap = nums[j] - nums[i];
//                if (!visited[i].containsKey(gap)) {
                int[] arr = new int[]{1, 0, 0};
                solve(j, gap, 2);
                result += visited[j].get(gap)[2] + visited[j].get(gap)[1];
//                }
                // 已经存在的直接跳过
            }
        }
        return result;
    }

    private void solve(int j, int gap, int count) {
        if (visited[j] == null) {
            visited[j] = new HashMap<>();
        } else if (visited[j].containsKey(gap)) {
            return;
        }
        int target = nums[j] + gap;
        int[] arr = new int[]{1, 0, 0};

        for (int i = j + 1; i < size; i++) {
            if (nums[i] == target) {
                solve(i, gap, count + 1);
                int[] list = visited[i].get(gap);
                arr[1] += 1;
                arr[2] += list[1] + list[2];
            }
        }
        visited[j].put(gap, arr);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.numberOfArithmeticSlices(new int[]{7, 7, 7, 7, 7});
        System.out.println(ret);
    }
}