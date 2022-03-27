package leetcode.editor.contest._5253_kthPalindrome;

class Solution {
    public long[] kthPalindrome(int[] queries, int intLength) {
        // 需要通过位置和长短直接获取回文数字
        long[] ans = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = solve(intLength, queries[i]);
        }
        return ans;
    }

    private long solve(int length, int rank) {
        int maxRank = 9;
        int tens = 1;
        for (int i = 0; i < divide(length - 2); i++) {
            tens *= 10;
        }
        maxRank *= tens;
        if (rank > maxRank) {
            return -1;
        }
        rank--;
        int[] ansChars = new int[divide(length)];
        ansChars[0] = rank / tens + 1;
        rank %= tens;
        tens /= 10;
        for (int i = 1; i < length && tens > 0; i++) {
            ansChars[i] = rank / tens;
            rank %= tens;
            tens /= 10;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ansChars.length; i++) {
            stringBuilder.append(ansChars[i]);
        }
        int start = ansChars.length - 1;
        if (length % 2 != 0) {
            start--;
        }
        for (int i = start; i >= 0; i--) {
            stringBuilder.append(ansChars[i]);
        }
        return Long.parseLong(stringBuilder.toString());
    }

    private int divide(int num) {
        int ans = num / 2;
        if (ans * 2 < num) {
            ans += 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.kthPalindrome(new int[]{1, 2, 3, 4, 5, 90}, 3);
    }
}