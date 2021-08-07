package leetcode.editor._457_circularArrayLoop;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int size = nums.length;
        boolean[] visit = new boolean[size];
        for (int i = 0; i < size; i++) {
            if (visit[i]) {
                continue;
            }
            visit[i] = true;
            boolean isPositive = nums[i] > 0;
            int j = i + nums[i];
            j = control(j, size);
            if (i == j) {
                continue;
            }
            while (true) {
                boolean temp = nums[j] > 0;
                if (temp != isPositive) {
                    // 在此中断
                    break;
                }
                if (visit[j]) {
                    // 还需要判断长度
                    return true;
                }

                visit[j] = true;
                // 用于下一次的标识
                isPositive = temp;

                int nextJ = j + nums[j];
                nextJ = control(nextJ, size);
                if (nextJ == j) {
                    break;
                }
                j = nextJ;
            }
        }
        return false;
    }

    private int control(int curr, int size) {
        if (curr >= size) {
            curr -= size;
        } else if (curr < 0) {
            curr += size;
        }
        return curr;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean result = solution.circularArrayLoop(new int[]{-2, 1, -1, -2, -2});
        System.out.println(result);
    }
}