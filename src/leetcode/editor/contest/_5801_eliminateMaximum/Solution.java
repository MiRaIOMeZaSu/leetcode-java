package leetcode.editor.contest._5801_eliminateMaximum;

import java.util.Arrays;

class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        // 计算所有怪物在第几分钟到达城市(准确地说是最晚消灭怪物的时间点)
        int size = dist.length;
        double[] timePoint = new double[size];
        for (int i = 0; i < size; i++) {
            timePoint[i] = (double)dist[i] / (double)speed[i];
        }
        Arrays.sort(timePoint);
        int res = 0;
        while (res < size && res <= timePoint[res]) {
            res++;
        }
        return res;
    }
}