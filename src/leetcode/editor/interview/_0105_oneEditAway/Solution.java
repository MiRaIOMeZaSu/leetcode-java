package leetcode.editor.interview._0105_oneEditAway;

class Solution {
    public boolean oneEditAway(String first, String second) {
        if (first.equals(second)) {
            return true;
        }
        int a = first.length();
        int b = second.length();

        if (b == a) {
            boolean flag = true;
            for (int i = 0; i < a; i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    if (flag) {
                        flag = false;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } else if (a - 1 == b || a == b - 1) {
            int gapA = 0;
            int gapB = 0;
            int size = Math.min(a, b);
            int i = 0;
            while (i < size) {
                if (first.charAt(i + gapA) != second.charAt(i + gapB)) {
                    if (a - 1 == b) {
                        if (gapA == 1) {
                            return false;
                        }
                        i--;
                        gapA++;
                    } else {
                        // 增加了字符
                        if (gapB == 1) {
                            return false;
                        }
                        i--;
                        gapB++;
                    }
                }
                i++;
            }
            return true;
        }
        return false;
    }
}