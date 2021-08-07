package leetcode.editor._457_circularArrayLoop;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] > 1000) {
                continue;
            }
            boolean isPositive = nums[i] > 0;
            int j = i + nums[i];
            nums[i] = Integer.MAX_VALUE - i;
            j = control(j, size);
            if (i == j) {
                continue;
            }
            while (true) {
                if (nums[j] > 1000) {
                    // 还需要判断长度
                    if (nums[j] == Integer.MAX_VALUE - i) {
                        return true;
                    }
                    break;
                }

                boolean temp = nums[j] > 0;
                if (temp != isPositive) {
                    // 在此中断
                    break;
                }

                // 用于下一次的标识
                isPositive = temp;

                int nextJ = j + nums[j];
                nextJ = control(nextJ, size);
                if (nextJ == j) {
                    break;
                }
                nums[j] = Integer.MAX_VALUE - i;
                j = nextJ;
            }
        }
        return false;
    }

    private int control(int curr, int size) {
        while (curr >= size) {
            curr -= size;
        }

        while (curr < 0) {
            curr += size;
        }
        return curr;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean result = solution.circularArrayLoop(new int[]{-2, -3, -9});
        System.out.println(result);
    }
}