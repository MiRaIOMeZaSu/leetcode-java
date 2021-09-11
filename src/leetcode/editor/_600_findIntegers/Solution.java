package leetcode.editor._600_findIntegers;

class Solution {
    public int findIntegers(int n) {
        if (n == 1) {
            return 2;
        }
        int size = 0;
        int last = 0;
        int temp = n;
        while (temp != 0) {
            last = temp;
            temp = temp & (temp - 1);
        }
        // 此时last为最高位的数字
        temp = last;
        while (temp > 1) {
            temp = temp >> 1;
            size++;
        }
        int[][] table = new int[size][2];
        table[0][0] = 1;
        table[0][1] = 1;
        for (int i = 1; i < size; i++) {
            table[i][0] = table[i - 1][0] + table[i - 1][1];
            table[i][1] = table[i - 1][0];
        }
        // 前面的位数应该同样要保持不连续1
        int pivot = last >> 1;
        int length = size - 1;
        boolean flag = true;
        int result = table[size - 1][0] + table[size - 1][1];
        while (pivot >= 1) {
            if ((n | pivot) == n) {
                // 此位为1
                result += table[length][0];
                if (flag) {
                    break;
                }
                flag = true;
            } else {
                flag = false;
            }
            length--;
            pivot = pivot >> 1;
        }
        if (pivot == 0) {
            result += 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findIntegers(7);
    }
}