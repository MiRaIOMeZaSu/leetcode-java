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
        if (size % k == 0) {
            // 需要多次跳跃
            // 每次都会产生循环
            for (int i = 0; i < k; i++) {
                int curr = nums[i];
                int currIndex = i;
                for (int j = 0; j < size / k; j++) {
                    currIndex += k;
                    if (currIndex >= size) {
                        break;
                    }
                    int temp = nums[currIndex];
                    nums[currIndex] = curr;
                    curr = temp;
                }
                nums[i] = curr;
            }
        } else {
            // 只需要一次跳跃
            int curr = nums[0];
            int currIndex = 0;
            for (int i = 0; i < size - 1; i++) {
                currIndex += k;
                currIndex %= size;
                int temp = nums[currIndex];
                nums[currIndex] = curr;
                curr = temp;
            }
            nums[0] = curr;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.rotate(new int[]{1, 2, 3, 4, 5, 6}, 4);
    }
}