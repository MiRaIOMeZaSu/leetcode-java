package leetcode.editor._166_fractionToDecimal;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        // 使用map存储numerator
        boolean isPositive = true;
        Long numeratorL = (long) numerator;
        Long denominatorL = (long) denominator;
        if (numerator < 0) {
            isPositive = !isPositive;
            numeratorL = -numeratorL;
        }
        if (denominator < 0) {
            isPositive = !isPositive;
            denominatorL = -denominatorL;
        }
        Map<Long, Integer> map = new HashMap<>();
        String prex = String.valueOf(numeratorL / denominatorL);
        numeratorL = (numeratorL % denominatorL) * 10;
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        map.put(numeratorL, 0);
        index++;
        while (numeratorL != 0) {
            long token = numeratorL / denominatorL;
            numeratorL = (numeratorL % denominatorL) * 10;
            if (map.containsKey(numeratorL)) {
                stringBuilder.append(token);
                stringBuilder.insert(map.get(numeratorL), "(");
                stringBuilder.append(")");
                break;
            }
            stringBuilder.append(token);
            map.put(numeratorL, index);
            index++;
        }
        String ans = prex + (stringBuilder.length() == 0 ? "" : "." + stringBuilder.toString());
        if (ans.equals("0")) {
            return ans;
        }
        return (isPositive ? "" : "-") + ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.fractionToDecimal(0, -5);
    }
}