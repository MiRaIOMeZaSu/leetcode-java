package leetcode.editor._583_minDistance;

class Solution {
    public int minDistance(String word1, String word2) {
        // 直接寻找最长公共子序列
        int longSize, shortSize;
        char[] longWord, shortWord;
        int len1 = word1.length();
        int len2 = word2.length();
        boolean flag = len1 > len2;
        longSize = flag ? len1 : len2;
        shortSize = flag ? len2 : len1;
        longWord = flag ? word1.toCharArray() : word2.toCharArray();
        shortWord = flag ? word2.toCharArray() : word1.toCharArray();
        int[][] dp = new int[shortSize][2];
        int ans = 0;
        for (int i = 0; i < longSize; i++) {
            if (longWord[i] == shortWord[0]) {
                dp[0][0] = 1;
                dp[0][1] = i;
                ans = 1;
                break;
            }
        }
        if (dp[0][0] == 0) {
            dp[0][1] = -1;
        }
        for (int i = 1; i < shortSize; i++) {
            int minIndex = longSize - 1;
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j][0] == 0) {
                    continue;
                }
                if (dp[i][0] <= dp[j][0] + 1) {
                    minIndex = Math.min(minIndex, dp[j][1]);
                    for (int k = dp[j][1] + 1; k < longSize; k++) {
                        if (longWord[k] == shortWord[i]) {
                            if (dp[i][0] < dp[j][0] + 1) {
                                dp[i][0] = dp[j][0] + 1;
                                dp[i][1] = k;
                            } else {
                                dp[i][1] = Math.min(k, dp[i][1]);
                            }
                            break;
                        }
                    }
                }
            }
            if (dp[i][0] == 0) {
                for (int j = 0; j <= minIndex; j++) {
                    if (longWord[j] == shortWord[i]) {
                        dp[i][0] = 1;
                        dp[i][1] = j;
                        break;
                    }
                }
            }
            if (dp[i][0] == 0) {
                dp[i][1] = -1;
            }
            ans = Math.max(ans, dp[i][0]);
        }
        return longSize + shortSize - (ans * 2);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minDistance("dinitrophenylhydrazine",
                "acetylphenylhydrazine"));
    }
}