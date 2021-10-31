package leetcode.editor.leetcode._5917_possiblyEquals;

class Solution {
    String s1, s2;
    boolean[][][][] visit;
    boolean flag = false;

    public boolean possiblyEquals(String s1, String s2) {
        // 连续数字不超过3,分割数字判断情况
        // 直接通过一个判断另一个
        // 使用递归法判断字母数字的情况
        // 不会出现数字0
        this.s1 = s1;
        this.s2 = s2;
        visit = new boolean[s1.length() + 1][s2.length() + 1][1000][1000];
        solve(0, 0, 0, 0);
        return flag;
    }

    private void solve(int index1, int index2, int rest1, int rest2) {
        if (flag) {
            return;
        }
        if ((index1 >= s1.length() && rest2 > rest1) || (index2 >= s2.length() && rest1 > rest2)) {
            return;
        }
        if (visit[index1][index2][rest1][rest2]) {
            return;
        }
        visit[index1][index2][rest1][rest2] = true;
        // 开始根据条件判断
        // 首先保证不剩数字
        boolean a = index1 < s1.length() && Character.isDigit(s1.charAt(index1));
        boolean b = index2 < s2.length() && Character.isDigit(s2.charAt(index2));
        if (a || b) {
            // 至少一边存在数字
            if (a) {
                for (int i = 0; i < 3 && index1 + i < s1.length() && Character.isDigit(s1.charAt(index1 + i)); i++) {
                    solve(index1 + i + 1, index2, rest1 + Integer.parseInt(s1.substring(index1, index1 + i + 1)), rest2);
                }
            } else {
                for (int i = 0; i < 3 && index2 + i < s2.length() && Character.isDigit(s2.charAt(index2 + i)); i++) {
                    solve(index1, index2 + i + 1, rest1, rest2 + Integer.parseInt(s2.substring(index2, index2 + i + 1)));
                }
            }
            return;
        }
        if (rest1 != 0 && rest2 != 0) {
            int temp = Math.min(rest1, rest2);
            solve(index1, index2, rest1 - temp, rest2 - temp);
        } else if (rest1 == 0 && rest2 == 0) {
            if (index1 == s1.length() || index2 == s2.length()) {
                if (index1 == s1.length() && index2 == s2.length()) {
                    flag = true;
                }
            } else if (s1.charAt(index1) == s2.charAt(index2)) {
                solve(index1 + 1, index2 + 1, 0, 0);
            }
        } else {
            // 此时只有一个rest不为零
            // 能够保证不存在数字
            if (rest1 != 0) {
                solve(index1, index2 + 1, rest1 - 1, 0);
            } else {
                solve(index1 + 1, index2, 0, rest2 - 1);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.possiblyEquals("l123e",
                "44"));
    }
}