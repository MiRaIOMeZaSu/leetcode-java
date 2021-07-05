package leetcode.editor._726_countOfAtoms;

import java.util.*;

class Solution {
    String string;
    int size;
    int index;
    Map<String, Integer> map = new TreeMap<>();

    public String countOfAtoms(String formula) {
        // 线段法?
        // 栈
        // 从后往前,用函数栈的方式
        string = formula;
        size = formula.length();
        index = size - 1;
        solve(1);
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            stringBuilder.append(key);
            int count = map.get(key);
            if (count != 1) {
                stringBuilder.append(count);
            }
        }
        return stringBuilder.toString();
    }

    private void solve(int times) {
        int pivot = 1;
        StringBuilder stringBuilder = new StringBuilder();
        while (index >= 0) {
            if (Character.isDigit(string.charAt(index))) {
                int num = 0;
                int mutil = 1;
                while (Character.isDigit(string.charAt(index))) {
                    num += mutil * (string.charAt(index) - '0');
                    mutil *= 10;
                    index--;
                }
                pivot = num;
                continue;
            } else if (Character.isLowerCase(string.charAt(index))) {
                stringBuilder.append(string.charAt(index));
            } else if (Character.isUpperCase(string.charAt(index))) {
                stringBuilder.append(string.charAt(index));
                stringBuilder.reverse();
                String key = stringBuilder.toString();
                map.put(key, map.getOrDefault(key, 0) + pivot * times);
                stringBuilder = new StringBuilder();
                pivot = 1;
            } else if (string.charAt(index) == ')') {
                index--;
                solve(times * pivot);
                pivot = 1;
                continue;
            } else if (string.charAt(index) == '(') {
                index--;
                return;
            }
            index--;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.countOfAtoms("Mg(OH)2");
    }
}