package leetcode.editor._5840_minSwaps;

import java.util.regex.Pattern;

class Solution {
    public int minSwaps(String s) {
        int i = 0;
        s.replaceAll("\\[\\]*", "");
        while (s.length() > 0) {
            for (; i < s.length() - 1; i++) {
                if (s.charAt(i) == '[' && s.charAt(i + 1) == ']') {
                    // 往两边寻找
                    int left = i;
                    int right = i + 1;
                    while (left >= 0 && right < s.length()) {
                        if (s.charAt(left) == '[' && s.charAt(right) == ']') {
                            left--;
                            right++;
                        } else {
                            break;
                        }
                    }
                    int j = 1;
                    int gap = right - left - 1;
                    int x = 0;
                    while (left + 1 + j * gap + x < s.length()) {
                        if (s.charAt(left + 1 + x) != s.charAt(left + 1 + j * gap + x)) {
                            break;
                        }
                        x++;
                        if (x == gap) {
                            j++;
                            x = 0;
                        }
                    }
                    right += gap * (j - 1);
                    String a = s.substring(0, left + 1);
                    String b = s.substring(right);
                    s = a + b;
                    i = left;
                    if(i < 0){
                        i = 0;
                    }
                    break;
                    // left到right均可移除
                }
            }
            if (i >= s.length() - 1) {
                break;
            }
        }
        return (int) Math.ceil((double) (s.length() >> 1) / (double) 2);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minSwaps("[[[]]]][][]][[]]][[[");
    }
}