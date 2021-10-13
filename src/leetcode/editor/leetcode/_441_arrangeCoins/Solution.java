package leetcode.editor._441_arrangeCoins;

class Solution {
    public int arrangeCoins(int n) {
        // sqrt
        long nL = n;
        long smol = (long)Math.sqrt(nL * 2);
        long curr = smol;
        while (true) {
            long currSum = (curr * (curr + 1)) / 2;
            if (n < currSum) {
                return (int) (curr - 1);
            } else if (n == currSum) {
                return (int) curr;
            }
            curr++;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.arrangeCoins(1804289383);
    }
}