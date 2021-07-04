package leetcode.editor.contest._5802_countGoodNumbers;

class Solution {
    public int countGoodNumbers(long n) {
        // 4和5的乘积
        // 计算n是2的多少次方
        long res = 1;
        if (n % 2 != 0) {
            res *= 5;
            n--;
        }
        int index = 1;
        long bit = 2;
        while (n > 0) {
            long temp = n;
            if ((n | bit) == temp) {
                n = bit ^ n;
                long toMulti = 20;
                for (int i = 0; i < index - 1; i++) {
                    toMulti *= toMulti;
                    if (toMulti >= 1000000007) {
                        toMulti %= 1000000007;
                    }
                }
                res *= toMulti;
                if (res >= 1000000007) {
                    res %= 1000000007;
                }
            }
            bit = bit << 1;
            index++;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.countGoodNumbers(50);
    }
}