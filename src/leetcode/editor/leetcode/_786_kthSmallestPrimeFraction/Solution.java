package leetcode.editor.leetcode._786_kthSmallestPrimeFraction;

import java.util.*;

class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        Queue<Map.Entry<Double, int[]>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Double, int[]>>() {
            @Override
            public int compare(Map.Entry<Double, int[]> o1, Map.Entry<Double, int[]> o2) {
                if (o2.getKey().equals(o1.getKey())) {
                    return 0;
                }
                if (o2.getKey() > o1.getKey()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j > i; j--) {
                double next = (double) arr[i] / (double) arr[j];
                if (queue.size() < k || next < queue.peek().getKey()) {
                    queue.add(new AbstractMap.SimpleEntry<>(next, new int[]{arr[i], arr[j]}));
                    // 每次移除最大的一个
                    while (queue.size() > k) {
                        queue.poll();
                    }
                } else {
                    break;
                }
            }
        }
        return queue.peek().getValue();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ans = solution.kthSmallestPrimeFraction(new int[]{1, 1669, 1721, 3821, 4397, 5861, 6521, 6907, 13043, 14929, 16741, 17387, 17431, 17837, 21737, 22111, 24109, 24593, 27509, 27743}, 115);
        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }
}