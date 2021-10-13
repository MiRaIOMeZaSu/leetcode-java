package leetcode.editor._445_addTwoNumbers;

import java.util.Comparator;
import java.util.PriorityQueue;

class Arr {
    public int[] arr;
    public int index;

    Arr(int[] arr) {
        this.arr = arr;
        this.index = 0;
    }

    public int getHead() {
        return this.arr[this.index];
    }
}

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int count = 0;
        int n = matrix.length;
        PriorityQueue<Arr> queue = new PriorityQueue<>(new Comparator<Arr>() {
            @Override
            public int compare(Arr o1, Arr o2) {
                return o1.getHead() - o2.getHead();
            }
        });
        for (int i = 0; i < n; i++) {
            queue.add(new Arr(matrix[i]));
        }
        int ret;
        Arr arr = queue.poll();
        ret = arr.getHead();
        arr.index++;
        if (arr.arr.length > arr.index) {
            queue.add(arr);
        }
        count++;
        while (count < k) {
            arr = queue.poll();
            ret = arr.getHead();
            arr.index++;
            if (arr.arr.length > arr.index) {
                queue.add(arr);
            }
            count++;
        }
        return ret;
    }
}