package leetcode.editor.contest._5893_smallestSubsequence;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = s.length();
        int[] count = new int[size];
        char[] chars = s.toCharArray();
        count[size - 1] = chars[size - 1] == letter ? 1 : 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (chars[i] == letter) {
                list.add(i);
            }
        }
        for (int i = size - 2; i >= 0; i--) {
            count[i] = count[i + 1];
            if (chars[i] == letter) {
                count[i] += 1;
            }
        }
        if (k == repetition) {
            for (int i = 0; i < repetition; i++) {
                stringBuilder.append(letter);
            }
            return stringBuilder.toString();
        }
        int restNeedle = repetition;
        int tailIndex = list.get(list.size() - restNeedle);
        // tailEgdeIndex会被包含
        int tailEgdeIndex = tailIndex;
        int normalNeedle = k - 1 - stringBuilder.length();
        int normalRest = (size - 1) - (tailIndex + 1) + 1;
        if (normalRest < normalNeedle) {
            tailEgdeIndex -= normalNeedle - normalRest;
        }
        int headEgdeIndex = 0;
        char last = chars[headEgdeIndex];
        int lastIndex = headEgdeIndex;
        if (k - stringBuilder.length() == restNeedle) {
            for (int i = 0; i < restNeedle; i++) {
                stringBuilder.append(letter);
            }
            return stringBuilder.toString();
        }
        while (stringBuilder.length() < k) {
            for (int i = headEgdeIndex + 1; i <= tailEgdeIndex; i++) {
                if (chars[i] < last) {
                    last = chars[i];
                    lastIndex = i;
                }
            }
            stringBuilder.append(last);
            if (last == letter) {
                restNeedle--;
                if (restNeedle == 0) {
                    break;
                }
                tailIndex = list.get(list.size() - restNeedle);
                tailEgdeIndex = tailIndex;
                normalNeedle = k - 1 - stringBuilder.length();
                normalRest = (size - 1) - (tailIndex + 1) + 1;
                if (normalRest < normalNeedle) {
                    tailEgdeIndex -= normalNeedle - normalRest;
                }
            } else {
                tailEgdeIndex++;
                while (tailEgdeIndex >= size) {
                    tailEgdeIndex--;
                }
                while (count[tailEgdeIndex] < restNeedle) {
                    tailEgdeIndex--;
                }
            }
            if (k - stringBuilder.length() == restNeedle) {
                for (int i = 0; i < restNeedle; i++) {
                    stringBuilder.append(letter);
                }
                return stringBuilder.toString();
            }
            headEgdeIndex = lastIndex + 1;
            last = chars[headEgdeIndex];
            lastIndex = headEgdeIndex;
        }
        if (stringBuilder.length() == k) {
            return stringBuilder.toString();
        }
        // 继续填充
        restNeedle = k - stringBuilder.length();
        // 包含tailEgdeIndex
        tailEgdeIndex = (size - 1) - restNeedle + 1;
        headEgdeIndex = lastIndex + 1;
        last = chars[headEgdeIndex];
        lastIndex = headEgdeIndex;
        while (stringBuilder.length() < k) {
            for (int i = headEgdeIndex + 1; i <= tailEgdeIndex; i++) {
                if (chars[i] < last) {
                    last = chars[i];
                    lastIndex = i;
                }
            }
            stringBuilder.append(last);
            if (stringBuilder.length() == k) {
                return stringBuilder.toString();
            }
            tailEgdeIndex++;
            headEgdeIndex = lastIndex + 1;
            last = chars[headEgdeIndex];
            lastIndex = headEgdeIndex;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.smallestSubsequence("iaiiiijjiqibiiiikliiibbiiffhiniqisiwiiidiggijjiiisiiziiiiikiiiiiiqiiibiiegiliouxichiiiiiiiuuiiigikkliiiiriiiziijiiniiiiiistiiiziiiilliiiyiiikiiiiiiiiiiiiifihhiipiiiuuybciiiiiiiiiiiiiziiii", 136, 'i', 124));
    }
}