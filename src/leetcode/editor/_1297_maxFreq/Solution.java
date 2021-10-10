package leetcode.editor._1297_maxFreq;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Util {
    static char zero = 'a';

    public static int charToInt(char ch) {
        return ch - 'a';
    }

    public static char IntToChar(int i) {
        return (char) (zero + i);
    }
}

class Solution {
    static final char start = 'a';

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int size = s.length();
        int[] currAlp = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < minSize; i++) {
            currAlp[Util.charToInt(chars[i])]++;
        }
        Map<String, Integer> map = new HashMap<>();
        int curr = 0;
        for (int i = 0; i < 26; i++) {
            if (currAlp[i] > 0) {
                curr++;
            }
        }
        if (curr <= maxLetters) {
            map.put(s.substring(0, minSize), 1);
        }
        // 开始滑动
        for (int i = 1; i + minSize - 1 < size; i++) {
            char nextCh = chars[i + minSize - 1];
            char lastCh = chars[i - 1];
            int lastIndex = Util.charToInt(lastCh);
            int nextIndex = Util.charToInt(nextCh);
            if (currAlp[lastIndex] == 1) {
                curr--;
            }
            currAlp[lastIndex]--;
            if (currAlp[nextIndex] == 0) {
                curr++;
            }
            currAlp[nextIndex]++;
            if (curr > maxLetters) {
                continue;
            }
            // 可以添加
            map.merge(s.substring(i, i + minSize), 1, Integer::sum);
        }
        int ans = 0;
        Iterator<Integer> integerIterator = map.values().iterator();
        while (integerIterator.hasNext()) {
            int count = integerIterator.next();
            if (count > ans) {
                ans = count;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.maxFreq(
                "ffcbcecaaeaafcb", 1, 8, 10);
    }
}