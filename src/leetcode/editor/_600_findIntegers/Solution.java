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

        int pivot = 1;
        int length = 0;
        int result = table[size - 1][0] + table[size - 1][1] + 1;
        while (pivot != last) {
            if ((n | pivot) == n) {
                // 此位为1
                result += table[length][0];
            }
            length++;
            pivot = pivot << 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findIntegers(53);
    }
}