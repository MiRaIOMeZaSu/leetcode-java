package leetcode.editor._726_countOfAtoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    String string;
    int size;
    int index = 0;

    public String countOfAtoms(String formula) {
        // 线段法?
        // 栈
        List<Integer> head = new ArrayList<>();
        List<Integer> tail = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        size = formula.length();
        int i = 0;
        while (i < size) {
            if (Character.isUpperCase(formula.charAt(i))) {
                int start = i;
                i++;
                while (i < size && Character.isLowerCase(formula.charAt(i))) {
                    i++;
                }
                map.put(formula.substring(start, i), 0);
            } else if (i < size && Character.isDigit(formula.charAt(i))) {
                i++;
            } else if (formula.charAt(i) == '(') {
                head.add(i);
                i++;
            } else if (formula.charAt(i) == ')') {
                tail.add(i);
                i++;
                int num = 0;
                while (i < size && Character.isDigit(formula.charAt(i))) {
                    num *= 10;
                    num += formula.charAt(i);
                    i++;
                }
                if (num == 0) {
                    num = 1;
                }
                nums.add(num);
            }
        }
        // 从此开始线段划分
        for (i = 0; i < nums.size(); i++) {

        }
        return "";
    }
}