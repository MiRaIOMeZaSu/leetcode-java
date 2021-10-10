package leetcode.editor.offer._64_sumNums;

class Solution {
    public int sumNums(int n) {
        // 加和后再进位
        int ret = 0;
        int bit = 16384;
        ret += bit & n;
        ret = ret << 1;
        bit = bit >> 1;
        return 1;
    }
}