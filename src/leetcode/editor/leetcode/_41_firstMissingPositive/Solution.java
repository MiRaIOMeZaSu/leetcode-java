package leetcode.editor._41_firstMissingPositive;

class Solution {
    public int firstMissingPositive(int[] nums) {
        int size = nums.length;
        int index = 0;
        while (index < size) {
            int position = nums[index] - 1;
            if (index != position && position >= 0 && position < size && nums[position] != nums[index]) {
                exchange(nums, index, position);
                continue;
            }
            index++;
        }
        for (int i = 0; i < size; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return size + 1;
    }

    private void exchange(int[] nums, int indexA, int indexB) {
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.firstMissingPositive(new int[]{1, 1}));
    }
}