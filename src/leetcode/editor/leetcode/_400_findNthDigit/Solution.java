package leetcode.editor._400_findNthDigit;

class Solution {
    public int findNthDigit(int n) {
        // 9 - 99 - 999
        long curr = 9;
        int times = 1;
        long num = 1;
        long last = 0;
        while (n > curr) {
            num *= 10;
            times += 1;
            last = curr;
            curr += times * 9 * num;
        }
        // 当前位数为times
        int index = (int) (n - last);
        int startNum = (int) (num - 1);
        int targetNum = startNum + index / times;
        int rest = index % times;
        if (rest != 0) {
            targetNum += 1;
        }
        int ret;
        ret = targetNum % 10;
        targetNum /= 10;
        if (rest == 0) {
            return ret;
        }
        for (int i = 0; i < times - rest; i++) {
            ret = targetNum % 10;
            targetNum /= 10;
        }
        return ret;
    }
}