package leetcode.editor._5840_minSwaps;

import java.util.regex.Pattern;

class Solution {
    public int minSwaps(String s) {
        int size;
        do {
            size = s.length();
            s = s.replaceAll("\\[\\]", "");
        } while (s.length() != size);
        return (int) Math.ceil((double) (s.length() >> 1) /(double) 2);
    }
}