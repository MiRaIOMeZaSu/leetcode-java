package leetcode.editor.interview._1714_smallestK;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

class Solution {
    public int[] smallestK(int[] arr, int k) {
        if(k==0){
            return new int[0];
        }
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            q.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < q.peek()) {
                q.poll();
                q.add(arr[i]);
            }
        }
        int[] result = new int[k];
        Iterator<Integer> it = q.iterator();
        int i = 0;
        while (it.hasNext()) {
            result[i] = it.next();
            i++;
        }
        return result;
    }
}