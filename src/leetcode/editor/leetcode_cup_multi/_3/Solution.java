package leetcode.editor.leetcode_cup_multi._3;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

class Solution {
    public int[] volunteerDeployment(int[] finalCnt, long totalNum, int[][] edges, int[][] plans) {
        // 领接表
        int size = finalCnt.length + 1;
        List<Integer>[] table = new ArrayList[size];
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][0];
            if (table[a] == null) {
                table[a] = new ArrayList<>();
            }
            if (table[b] == null) {
                table[b] = new ArrayList<>();
            }
            table[a].add(b);
            table[b].add(a);

        }
        return new int[]{};
    }
}