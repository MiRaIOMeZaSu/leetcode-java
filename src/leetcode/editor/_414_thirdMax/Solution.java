package leetcode.editor._414_thirdMax;

class Solution {
    public int thirdMax(int[] nums) {
        int size = nums.length;
        int i = 0;
        int numCount = 0;
        int tailIndex = size;
        while (numCount < 3 && i < size) {
            int pivot = nums[0];
            int index = 0;
            for (int j = 1; j < size - i; j++) {
                if (pivot < nums[j]) {
                    pivot = nums[j];
                    index = j;
                }
            }
            exchange(nums, index, size - i - 1);
            if (tailIndex == size || nums[tailIndex] > nums[tailIndex - 1]) {
                numCount++;
            }
            tailIndex--;
            i++;
        }
        if (numCount >= 3) {
            return nums[tailIndex];
        }
        return nums[size - 1];
    }

    private void exchange(int[] nums, int indexA, int indexB) {
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.thirdMax(new int[]{2, 2, 3, 1}));
    }
}