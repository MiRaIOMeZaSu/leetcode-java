package leetcode.editor._313_nthSuperUglyNumber;

class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        // 使用指针法(10^8)
        // 最小堆
        // 动态规划?
        int size = primes.length;
        int[] indexs = new int[size];
        int[] result = new int[n + 1];
        for (int i = 0; i < size; i++) {
            indexs[i] = 1;
        }
        result[1] = 1;
        for (int i = 2; i <= n; i++) {
            int primeIndex = 0;
            int pivot = result[indexs[0]] * primes[0];
            for (int j = 1; j < size; j++) {
                int temp = result[indexs[j]] * primes[j];
                if (temp < pivot) {
                    primeIndex = j;
                    pivot = temp;
                }
            }
            indexs[primeIndex]++;
            if (pivot != result[i - 1]) {
                result[i] = pivot;
            } else {
                i--;
            }
        }
        return result[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19});
    }
}