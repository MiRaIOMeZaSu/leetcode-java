package leetcode.editor._1031_maxSumTwoNoOverlap;

class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        // 前缀法,后缀法
        // 先把短的全部找一遍
        // 只要求找和
        int shortLen = Math.min(firstLen, secondLen);
        int longLen = Math.max(firstLen, secondLen);
        // 正向反向都来一遍
        int size = nums.length;
        if (size < shortLen + longLen) {
            return 0;
        }
        int[] leftMax = new int[size];
        int[] rightMax = new int[size];
        // 正向
        int currSum = 0;
        for (int i = 0; i < size - 1; i++) {
            currSum += nums[i];
            if (i - shortLen >= 0) {
                currSum -= nums[i - shortLen];
            }
            if (i + 1 >= shortLen) {
                leftMax[i + 1] = Math.max(leftMax[i], currSum);
            }
        }
        // 反向
        currSum = 0;
        for (int i = size - 1; i > 0; i--) {
            currSum += nums[i];
            if (i + shortLen < size) {
                currSum -= nums[i + shortLen];
            }
            if (size - i + 1 > shortLen) {
                rightMax[i - 1] = Math.max(rightMax[i], currSum);
            }
        }
        currSum = 0;
        int ans = 0;
        for (int i = 0; i < size; i++) {
            currSum += nums[i];
            if (i - longLen >= 0) {
                currSum -= nums[i - longLen];
            }
            if(i - longLen + 1 >= 0){
                ans = Math.max(ans, currSum + Math.max(leftMax[i - longLen + 1], rightMax[i]));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxSumTwoNoOverlap(new int[]{}, 998, 1));
    }
}