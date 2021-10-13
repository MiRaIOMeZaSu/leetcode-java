package leetcode.editor._5844_minNonZeroProduct;

class Solution {
    private int pivot = 1000000007;

    public int minNonZeroProduct(int p) {
        // 奇数与偶数
        // 排列组合!
        if (p == 1) {
            return 1;
        }
        long first = 1;
        for (int i = 0; i < p; i++) {
            first *= 2;
        }
        first--;
        long second = first - 1;
        // 一共有countDown位的1
        int countDown = p - 1;
        if (second >= pivot) {
            second %= pivot;
        }
        long temp = 1;
        for (int i = 0; i < countDown - 1; i++) {
            temp *= second;
            if (temp >= pivot) {
                temp %= pivot;
            }
            temp *= temp;
            if (temp >= pivot) {
                temp %= pivot;
            }
        }
        temp *= second;
        if (temp >= pivot) {
            temp %= pivot;
        }
        if (first >= pivot) {
            first %= pivot;
        }
        first *= temp;
        if (first >= pivot) {
            first %= pivot;
        }
        return (int) first;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minNonZeroProduct(60);
    }
}