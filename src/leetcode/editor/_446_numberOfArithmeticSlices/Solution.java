package leetcode.editor._446_numberOfArithmeticSlices;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    private Set<Integer>[] visited;
    private int[] nums;
    private int size;
    private int result = 0;

    public int numberOfArithmeticSlices(int[] nums) {
        // 用邻接表存储已经计算过的
        // 某个区间内相同的数字如何处理? - 使用下标存储情况
        // 使用动态规划?存储到后面的长度!
        size = nums.length;
        this.nums = nums;
        visited = new HashSet[size];

        for (int i = 0; i < size - 2; i++) {
            for (int j = i + 1; j < size - 1; j++) {
                // 接下来往后找合适的
                if (visited[i] == null) {
                    visited[i] = new HashSet<>();
                }
                if (!visited[i].contains(j)) {
                    visited[i].add(j);
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    solve(j, nums[j] - nums[i], 2, temp);
                }
                // 已经存在的直接跳过
            }
        }
        return result;
    }

    private void solve(int j, int gap, int count, List<Integer> temp) {
        int target = nums[j] + gap;
        for (int i = j + 1; i < size; i++) {
            if (nums[i] == target) {
                if (visited[j] == null) {
                    visited[j] = new HashSet<>();
                }
                visited[j].add(i);
                temp.add(i);
                solve(i, gap, count + 1, temp);
                System.out.println(temp);
                temp.remove(temp.size() - 1);
                // 至少每个尾部都是独一无二的
                result += (count + 1 - 2);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.numberOfArithmeticSlices(new int[]{7, 7, 7, 7, 7});
        System.out.println(ret);
    }
}