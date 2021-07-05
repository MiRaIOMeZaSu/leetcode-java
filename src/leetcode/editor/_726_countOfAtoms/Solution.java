package leetcode.editor._726_countOfAtoms;

class Solution {
    String string;
    int size;
    int index = 0;

    public String countOfAtoms(String formula) {
        // 线段法?
        // 栈
        string = formula;
        size = formula.length();
//        return solve();
        return "";
    }

    private int solve() {
        int res = 0;
        int i = index;
        while (i < size) {
            if (string.charAt(i) == '(') {
                index = i + 1;
                int temp = solve();
                int num = 0;
                while (Character.isDigit(string.charAt(i))) {
                    num *= 10;
                    num += string.charAt(i);
                    i++;
                }
                num = (num == 0 ? 1 : num);
                res += temp * num;
            } else if (string.charAt(i) == ')') {
                index = i + 1;
                return res;
            } else if (Character.isUpperCase(string.charAt(i))) {
                // 直接跳到下一个为止
                i++;
                while (Character.isLowerCase(string.charAt(i))) {
                    i++;
                }
                int num = 0;
                while (Character.isDigit(string.charAt(i))) {
                    num *= 10;
                    num += string.charAt(i);
                    i++;
                }
                num = (num == 0 ? 1 : num);
                res += num;
            }
            i++;
        }
        return res;
    }
}