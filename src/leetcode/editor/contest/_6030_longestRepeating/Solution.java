package leetcode.editor.contest._6030_longestRepeating;

class Solution {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        // 均为10^5
        // 并查集
        // 存储每个字符串的左和右
        // 不仅会变大还会变小!
        char[] sChars = s.toCharArray();
        int[][] leftAndRightSet = new int[s.length()][2];
        for (int i = 0; i < s.length(); i++) {
            leftAndRightSet[i][0] = i;
            if (i > 0 && sChars[i] == sChars[i - 1]) {
                leftAndRightSet[i][0] = leftAndRightSet[i - 1][0];
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            leftAndRightSet[i][1] = i;
            if (i < s.length() - 1 && sChars[i] == sChars[i + 1]) {
                leftAndRightSet[i][1] = leftAndRightSet[i + 1][1];
            }
        }
        // 此时每个字符都能算出自己所处的长度
        int currMax = 0;
        for (int i = 0; i < s.length(); ) {
            currMax = leftAndRightSet[i][1] - leftAndRightSet[i][0] + 1;
            i = leftAndRightSet[i][1] + 1;
        }
        int[] ans = new int[queryCharacters.length()];
        char[] qChars = queryCharacters.toCharArray();
        for (int i = 0; i < queryCharacters.length(); i++) {
            if (qChars[i] == sChars[queryIndices[i]]) {
                continue;
            }
            sChars[queryIndices[i]] = qChars[i];
            // 此时已经变化,进行判断
            int left = queryIndices[i];
            int right = queryIndices[i];
            if (queryIndices[i] > 0 && queryIndices[i] < s.length()
                    && qChars[i] == sChars[queryIndices[i] - 1]
                    && qChars[i] == sChars[queryIndices[i] + 1]) {
                // 往两边延展
                left = leftAndRightSet[queryIndices[i] - 1][0];
                right = leftAndRightSet[queryIndices[i] + 1][1];
            }
            if (queryIndices[i] > 0 && qChars[i] == sChars[queryIndices[i] - 1]) {
                left = leftAndRightSet[queryIndices[i] - 1][0];
            }
            if (queryIndices[i] < s.length() - 1 && qChars[i] == sChars[queryIndices[i] + 1]) {
                right = leftAndRightSet[queryIndices[i] + 1][1];
            }
            for (int j = left; j <= queryIndices[i]; j++) {
                leftAndRightSet[j][1] = right;
            }
            for (int j = queryIndices[i]; j <= right; j++) {
                leftAndRightSet[j][0] = left;
            }
            currMax = Math.max(currMax, right - left + 1);
            ans[i] = currMax;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.longestRepeating("babacc",
                "bcb",
                new int[]{1, 3, 3});
        solution.longestRepeating("abyzz",
                "aa",
                new int[]{2, 1});
        solution.longestRepeating("mm",
                "bfviuwsr",
                        new int[]{0,0,1,0,0,1,1,0});
    }
}