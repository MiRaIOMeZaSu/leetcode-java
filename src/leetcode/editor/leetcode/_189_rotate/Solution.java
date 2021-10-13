package leetcode.editor.leetcode._189_rotate;

class Solution {
    public void rotate(int[] nums, int k) {
        if (k == 0) {
            return;
        }
        int size = nums.length;
        if (size == k) {
            return;
        }
        // 需要多次跳跃
        // 假设每次都会产生循环
        int count = 0;
        for (int i = 0; i < k && count < size - 1; i++) {
            int curr = nums[i];
            int currIndex = i;
            for (; count < size - 1; count++) {
                currIndex += k;
                currIndex %= size;
                if (currIndex == i) {
                    break;
                }
                int temp = nums[currIndex];
                nums[currIndex] = curr;
                curr = temp;
            }
            nums[i] = curr;
            count++;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.rotate(new int[]{1, 2, 3, 4, 5, 6}, 4);
    }
}