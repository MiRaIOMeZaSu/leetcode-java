package leetcode.editor.offer._60_dicesProbability;

import java.util.List;

class Solution {
    public double[] dicesProbability(int n) {
        // 01背包动态规划?
        // 结果为对称的
        double totalWay = Math.pow(6, n);
        int size = n * 5 + 1;
        double[] res = new double[size];
        double p = 0;
        res[0] = 1 / totalWay;
        res[size - 1] = res[0];
        p += res[0] * 2;
        int pivot = size >> 1;
        for (int i = 1; i < pivot; i++) {
            res[i] = (double) getWay(n + i, n, 1, 6) / totalWay;
            res[size - 1 - i] = res[i];
            p += res[i] * 2;
        }
        if (pivot * 2 != size) {
            res[pivot] = 1 - p;
        }
        return res;
    }

    private int getWay(int num, int dicesCount, int curr, int index) {
        if (index == 1) {
            if (num == dicesCount) {
                return curr;
            }
            return 0;
        }
        int pivot = num / index;
        int ret = 0;
        for (int i = pivot; i >= 0; i--) {
            int temp = curr * getCount(i, dicesCount);
            ret += getWay(num - i * index, dicesCount - i, temp, index - 1);
        }
        return ret;
    }

    private int getCount(int local, int total) {
        int ret = 1;
        local = Math.max(local, total - local);
        if (local == total) {
            return ret;
        } else if (local == total - 1) {
            return total;
        }
        for (int i = total; i > local; i--) {
            ret *= i;
        }
        for (int i = total - local; i > 1; i--) {
            ret /= i;
        }
        return ret;
    }
}