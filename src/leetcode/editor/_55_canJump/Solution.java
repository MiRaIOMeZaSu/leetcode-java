package leetcode.editor._55_canJump;

class Solution {
    public boolean canJump(int[] nums) {
        int size = nums.length;
        int curr = 0;
        for (int i = 0; i < size; i++) {
            if (curr < i) {
                break;
            }
            curr = Math.max(curr, i + nums[i]);
        }
        return curr >= size - 1;
    }
}