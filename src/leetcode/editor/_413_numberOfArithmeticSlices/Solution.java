package leetcode.editor._413_numberOfArithmeticSlices;

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int size = nums.length;
        if (size < 3) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < size - 2; i++) {
            if (nums[i + 1] - nums[i] == nums[i + 2] - nums[i + 1]) {
                int start = i;
                int gap = nums[i + 1] - nums[i];
                // 此时满足等差序列,继续拓展
                for (i = i + 3; i < size; i++) {
                    if (nums[i] - nums[i - 1] != gap) {
                        break;
                    }
                }
                result += times(i - start);
                i-=2;
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.numberOfArithmeticSlices(new int[]{1, 2, 3, 5, 7});
    }
}