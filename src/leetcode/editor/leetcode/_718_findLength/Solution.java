package leetcode.editor._718_findLength;

class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        // 求最长子数组
        // 类似子字符串?
        int longSize, shortSize;
        int[] longWord, shortWord;
        int len1 = nums1.length;
        int len2 = nums2.length;
        boolean flag = len1 > len2;
        longSize = flag ? len1 : len2;
        shortSize = flag ? len2 : len1;
        longWord = flag ? nums1 : nums2;
        shortWord = flag ? nums2 : nums1;
        Integer[] list = new Integer[100];
        for (int i = 0; i < longSize; i++) {
            int index = longWord[i];
            if (list[index] == null) {
                list[index] = i;
                continue;
            }
            list[index] = Math.min(i, list[index]);
        }
        int[][] dp = new int[shortSize][longSize];
//        int index = shortWord[0];
//        if (list[index] != null) {
//            dp[0][list[index]] = 1;
//        }
        int ans = 0;
//        if (longWord[0] == shortWord[0]) {
//            dp[0][0] = 1;
//        }
        for (int i = 0; i < longSize; i++) {
            if (shortWord[0] == longWord[i]) {
                dp[0][i] = 1;
                ans = 1;
            }
        }
        for (int i = 1; i < shortSize; i++) {
            int longIndex = longWord[0];
            int shortIndex = shortWord[i];
            if (longIndex == shortIndex) {
                dp[i][0] = 1;
            }
            for (int j = 1; j < longSize; j++) {
                if (longWord[j] == shortWord[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i][j]);
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findLength(new int[]{
                        0, 1, 0, 0, 0},
                new int[]{
                        0, 0, 1, 0, 0}));
    }
}