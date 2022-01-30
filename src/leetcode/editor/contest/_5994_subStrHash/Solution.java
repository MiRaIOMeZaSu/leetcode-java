package leetcode.editor.contest._5994_subStrHash;

class Solution {
    public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
        // 子串长度固定
        // 返回第一个
        // 使用窗口法
        int size = s.length();
        if (k == size) {
            return s;
        }
        int hashBeforeMod = 0;
        int currP = 1;
        for (int i = 0; i < k; i++) {
            hashBeforeMod %= modulo;
            currP %= modulo;
            hashBeforeMod += currP * toVal(s.charAt(i));
            currP *= power;
        }
        if (hashBeforeMod % modulo == hashValue) {
            return s.substring(0, k);
        }
        int i = 1;
        currP /= power;

        for (; i + k - 1 < size; i++) {
            hashBeforeMod -= toVal(s.charAt(i - 1));
            hashBeforeMod /= power;
            hashBeforeMod += toVal(s.charAt(i + k - 1)) * currP;
            if (hashBeforeMod % modulo == hashValue) {
                break;
            }
        }
        return s.substring(i, i + k);
    }

    int toVal(char ch) {
        int sub = ch - 'a';
        return sub + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String ans = solution.subStrHash("xmmhdakfursinye",
                96,
                45,
                15,
                21);
        System.out.println(ans);
    }
}