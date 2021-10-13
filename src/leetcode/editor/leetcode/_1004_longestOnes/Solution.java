package leetcode.editor._1004_longestOnes;

class Solution {
    public int longestOnes(int[] A, int K) {
        // 使用双指针
        int count = 0;
        int max = 0;
        int left = 0;
        int right = 0;
        while (left < A.length) {
            while (count <= K && left < A.length) {
                if (A[left] == 0) {
                    count++;
                }
                left++;
            }
            if (count > K) {
                max = Math.max(max, left - right - 1);
            } else {
                max = Math.max(max, left - right);
            }
            while (count > K && right <= left) {
                if (A[right] == 0) {
                    count--;
                }
                right++;
            }
        }
        return max;
    }
}