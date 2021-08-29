package leetcode.editor.contest._5856_minSessions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    int[] tasks;
    int sessionTime;
    int[] handle;
    int boxSize;

    public int minSessions(int[] tasks, int sessionTime) {
        Arrays.sort(tasks);
        this.tasks = tasks;
        this.sessionTime = sessionTime;
        int size = tasks.length;
        int total = 0;
        int right = size;
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            total += tasks[i];
            if (total > sessionTime && flag) {
                right -= (i - 1);
                flag = false;
            }
        }
        if (total <= sessionTime) {
            return 1;
        }
        int left = Math.max(2, total / sessionTime + ((total % sessionTime == 0) ? 0 : 1));
        while (left < right) {
            int mid = (left + right) >> 1;
            if (satisfy(mid)) {
                // 往左走
                right = mid;
            } else {
                // 往右走
                left = mid + 1;
            }
        }
        return right;
    }

    boolean satisfy(int boxSize) {
        this.boxSize = boxSize;
        handle = new int[boxSize];
        return solve(0);
    }

    boolean solve(int index) {
        if (index == tasks.length) {
            return true;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < boxSize; i++) {
            if (!set.contains(handle[i])) {
                set.add(handle[i]);
            } else {
                continue;
            }
            handle[i] += tasks[index];
            if (handle[i] <= sessionTime) {
                if (solve(index + 1)) {
                    return true;
                }
            }
            handle[i] -= tasks[index];
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minSessions(new int[]{9, 6, 9}, 14);
    }
}