package leetcode.editor._273_numberToWords;

import java.util.ArrayList;
import java.util.List;

class Solution {
    String[] one = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] oneStartTwo = new String[]{"Ten", "Eleven", "Twelve", "Thirteen",
            "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] two = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    int[] numBit = new int[10];
    List<String> stringList = new ArrayList<>();

    public String numberToWords(int num) {
        // 使用数组存储单位数字
        if (num == 0) {
            return "Zero";
        }
        for (int i = 0; i < 10; i++) {
            numBit[i] = num % 10;
            num /= 10;
        }
        if (numBit[9] != 0) {
            stringList.add(one[numBit[9]]);
            stringList.add("Billion");
        }
        // 使用3个数字转成指定的数字
        hundred(8, "Million");
        hundred(5, "Thousand");
        hundred(2, "");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            if (stringList.get(i).length() > 0) {
                stringBuilder.append(stringList.get(i));
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString().trim();
    }

    private void hundred(int start, String tail) {
        int a = start;
        int b = a - 1;
        int c = b - 1;
        if (numBit[a] + numBit[b] + numBit[c] != 0) {
            if (numBit[a] != 0) {
                stringList.add(one[numBit[a]]);
                stringList.add("Hundred");
            }
            if (numBit[b] == 1) {
                stringList.add(oneStartTwo[numBit[c]]);
            } else if (numBit[b] > 1) {
                stringList.add(two[numBit[b]]);
                if (numBit[c] != 0) {
                    stringList.add(one[numBit[c]]);
                }
            } else {
                stringList.add(one[numBit[c]]);
            }
            stringList.add(tail);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numberToWords(100000));
    }
}