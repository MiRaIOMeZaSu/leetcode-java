package leetcode.editor._470_rand10;

import java.util.Random;

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 *
 * @return a random integer in the range 1 to 7
 */
class SolBase {
    Random r = new Random();

    public int rand7() {
        return r.nextInt(7) + 1;
    }
}

class Solution extends SolBase {
    public int rand10() {
        int pivot = 4;
        do {
            pivot = rand7();
        } while (pivot == 4);
        int a = 6;
        do {
            a = rand7();
        } while (a > 5);
        if (pivot < 4) {
            return a;
        }
        return a + 5;
    }
}