package leetcode.editor._1833_maxIceCream;

class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int size = costs.length;
        if (size == 1) {
            if (coins >= costs[0]) {
                return 1;
            }
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += costs[i];
        }
        if (sum <= coins) {
            return size;
        }
        int res = 0;
        while (coins > 0) {
            int index = 0;
            for (int i = 1; i < size - res; i++) {
                if (costs[index] > costs[i]) {
                    index = i;
                }
            }
            if (coins < costs[index]) {
                return res;
            }
            coins -= costs[index];
            int temp = costs[index];
            costs[index] = costs[size - res - 1];
            costs[size - res - 1] = temp;
            res += 1;
        }
        return res;
    }
}