package leetcode.editor._1023_camelMatch;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        // 判断字符串是子字符串即可
        int size = queries.length;
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ans.add(solve(queries[i], pattern));
        }
        return ans;
    }

    private boolean solve(String query, String pattern) {
        // 遍历判断
        int qLen = query.length();
        int pLen = pattern.length();
        int pPointer = 0;
        char[] queryChars = query.toCharArray();
        char[] patternChars = pattern.toCharArray();
        for (int i = 0; i < qLen; i++) {
            char ch = queryChars[i];
            if (Character.isUpperCase(ch)) {
                // query提供的是大写

                // pattern匹配完了还有大写字母
                if (pPointer >= pLen) {
                    return false;
                }
                // 不匹配
                if (patternChars[pPointer] != ch) {
                    return false;
                }
                // 匹配
                pPointer++;
            } else {
                // query提供的是小写
                if (pPointer >= pLen) {
                    continue;
                }
                if (patternChars[pPointer] == ch) {
                    pPointer++;
                }
            }
        }
        return pPointer >= pLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solve("FooBarTest", "FB");
    }
}