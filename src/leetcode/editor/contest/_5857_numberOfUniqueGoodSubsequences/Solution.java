package leetcode.editor.contest._5857_numberOfUniqueGoodSubsequences;

class Solution {
    int pivot = 1000000007;

    public int numberOfUniqueGoodSubsequences(String binary) {
        // 由于数据量过大,无法通过哈希表计数
        // 子序列意味着数量
        // 使用动态规划!
        int size = binary.length();
        int[] dp = new int[size];
        dp[size - 1] = 1;
        int result = 1;
        for (int i = size - 2; i >= 0; i--) {

        }
        return result;
    }
}