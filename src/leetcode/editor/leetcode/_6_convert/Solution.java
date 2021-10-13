package leetcode.editor._6_convert;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public String convert(String s, int numRows) {
        // 当成多个V来看待
        // 使用多个列表存储,最后汇总到一起
        // 首先计算出V的大小
        // v < 1
        int size = s.length();
        List<Character>[] lists = new ArrayList[numRows];
        for (int i = 0; i < numRows; i++) {
            lists[i] = new ArrayList<>();
        }
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (index < size) {
            int prex = index;
            // 这一轮有numRows个
            for (; index < Math.min(size, prex + numRows); index++) {
                lists[index - prex].add(chars[index]);
            }
            prex = index;
            // 这一轮有numRows - 2个
            int currIndex = numRows - 2;
            for (; index < Math.min(size, prex + numRows - 2); index++) {
                lists[currIndex].add(chars[index]);
                currIndex--;
            }
        }
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < lists[i].size(); j++) {
                stringBuilder.append(lists[i].get(j));
            }
        }
        return stringBuilder.toString();
    }
}