package leetcode.editor._65_isNumber;

import java.util.regex.Pattern;

class Solution {
    public boolean isNumber(String s) {
        // 先判断是否有小数点
        // 对于小数,小数点前或后至少有一方有整数
        String hasPoint = ".*?\\..*?";
        String pattern = "([eE][+-]?\\d+)?";
        String mainPattern = "[+-]?\\d+";
        String pointPattern1 = "[+-]?\\d+\\.";
        String pointPattern2 = "[+-]?\\.\\d+";
        String pointPattern3 = "[+-]?\\d+\\.\\d+";
        if (Pattern.matches(hasPoint, s)) {
            // 包含小数点
            return Pattern.matches(pointPattern3 + mainPattern, s) ||
                    Pattern.matches(pointPattern2 + mainPattern, s)
                    || Pattern.matches(pointPattern1 + mainPattern, s);
        } else {
            // 不包含小数点
            return Pattern.matches(mainPattern + pattern, s);
        }
    }
}