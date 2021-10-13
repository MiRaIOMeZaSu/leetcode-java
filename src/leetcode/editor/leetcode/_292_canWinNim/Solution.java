package leetcode.editor._292_canWinNim;

import java.util.List;
import java.util.Random;

class Solution {
    public boolean canWinNim(int n) {
        if (n <= 3) {
            return true;
        }
        n -= 3;
        return n % 4 != 1;
    }

    private static void genNums() {
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            System.out.println(Math.abs(random.nextInt(Integer.MAX_VALUE - 1) + 1));
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.canWinNim(158);
        solution.genNums();
    }
}