package leetcode.editor.contest._6014_repeatLimitedString;

import java.util.HashMap;

class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        // 统计次数
        // 不必使用 s 中的全部字符
        // 首先是越长越好
        StringBuilder stringBuilder = new StringBuilder();
        char pivot = 'a';
        char[] keys = new char[26];
        keys[0] = pivot;
        for (int i = 1; i < 26; i++) {
            keys[i] = (char) (keys[i - 1] + 1);
        }
        int[] count = new int[26];
        int size = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < size; i++) {
            count[chars[i] - pivot]++;
        }
        int border = 25;
        while (stringBuilder.length() != size) {
            out:
            for (int j = 0; j < repeatLimit && count[border] > 0; j++) {
                stringBuilder.append(keys[border]);
                count[border]--;
            }
            if (count[border] == 0) {
                border--;
            } else {
                // 找一个冤大头作间隔
                boolean end = true;
                for (int i = border - 1; i >= 0; i--) {
                    if (count[i] != 0) {
                        stringBuilder.append(keys[i]);
                        count[i]--;
                        end = false;
                        break;
                    }
                }
                if (end) {
                    return stringBuilder.toString();
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.repeatLimitedString("cczazcc", 3));
        System.out.println(solution.repeatLimitedString("aababab", 2));
        System.out.println(solution.repeatLimitedString("aaaaaaab", 2));
    }
}