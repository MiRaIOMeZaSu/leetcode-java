package leetcode.editor._415_addStrings;

class Solution {
    public String addStrings(String num1, String num2) {
        // 加和
        int pointerL = Math.max(num1.length(), num2.length()) - 1;
        int pointerS = Math.min(num1.length(), num2.length()) - 1;
        String longString = num1.length() == pointerL + 1 ? num1 : num2;
        String shortString = num1.length() == pointerL + 1 ? num2 : num1;
        int nextBit = 0;
        char zero = '0';
        StringBuffer stringBuffer = new StringBuffer();
        int times = pointerS + 1;
        for (int i = 0; i < times; i++) {
            // 可以加和的范围内
            int a = longString.charAt(pointerL) - zero;
            int b = shortString.charAt(pointerS) - zero;
            pointerL--;
            pointerS--;
            int sum = a + b + nextBit;
            nextBit = sum / 10;
            stringBuffer.append(sum % 10);
        }
        for (int i = pointerL; i >= 0; i--) {
            int a = longString.charAt(i) - zero;
            int sum = a + nextBit;
            nextBit = sum / 10;
            stringBuffer.append(sum % 10);
        }
        if (nextBit != 0) {
            stringBuffer.append(nextBit);
        }
        stringBuffer.reverse();
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.addStrings("456", "77");
    }
}