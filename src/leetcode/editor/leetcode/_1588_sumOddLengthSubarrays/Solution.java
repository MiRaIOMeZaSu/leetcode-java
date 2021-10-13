package leetcode.editor._1588_sumOddLengthSubarrays;

class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int size = arr.length;
        int total = 0;
        for (int i = 0; i < size; i++) {
            int count = 1;
            for (int j = 3; j <= size; j += 2) {
                int a = Math.min(i + 1, j);
                int b = Math.max(j - (size - 1 - i), 1);
                if (a >= b) {
                    count += a - b + 1;
                }
            }
            total += count * arr[i];
        }
        return total;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.sumOddLengthSubarrays(new int[]{1,4,2,5,3});
    }
}