package leetcode.editor._201_rangeBitwiseAnd;

class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        if (left == right) {
            return left;
        }
        int ret = left;
        for (int i = left + 1; i <= right && ret != 0 && i >= 0; i++) {
            ret &= i;
        }
        return ret;
    }
}
