package leetcode.editor._1137_tribonacci;

class Solution {
    public int tribonacci(int n) {
        int[] arr = new int[]{0, 1, 1};
        if (n < 3) {
            return arr[n];
        }
        int i = 0;
        int j = 3;
        while (j < n) {
            int next = sum(arr);
            arr[i] = next;
            i++;
            if (i >= 3) {
                i -= 3;
            }
            j++;
        }
        return sum(arr);
    }

    private int sum(int[] arr) {
        int size = arr.length;
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }
        return result;
    }
}