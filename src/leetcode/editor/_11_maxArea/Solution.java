package leetcode.editor._11_maxArea;

class Solution {
    public int maxArea(int[] height) {
        // 重点之一:从两边往中间取
        int size = height.length;
        int left = 0;
        int right = size - 1;
        int ans = 0;
        do {
            ans = Math.max(ans, (right - left) * Math.min(height[left], height[right]));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        } while (left < right);
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ret = solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7});
        System.out.println(ret);
    }
}