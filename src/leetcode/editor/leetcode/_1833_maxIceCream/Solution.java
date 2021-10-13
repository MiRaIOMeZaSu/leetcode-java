package leetcode.editor._1833_maxIceCream;

import java.util.Arrays;

class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int index = 0;
        int size = costs.length;
        int sum = 0;
        while (index < size && sum + costs[index] <= coins) {
            sum += costs[index];
            index++;
        }
        return index;
    }
}