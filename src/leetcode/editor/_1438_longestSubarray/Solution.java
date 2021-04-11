package leetcode.editor._1438_longestSubarray;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.logging.Filter;

class Solution {
    int ret = 1;

    public int longestSubarray(int[] nums, int limit) {
        int size = nums.length;
        int left = 0;
        int right = 0;
        int[][] map = new int[size][2];
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        PriorityQueue<int[]> maxQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o2[0] - o1[0];
            }
            return o1[1] - o2[1];
        });
        map[left] = new int[]{nums[left], left};
        maxQueue.add(map[left]);
        minQueue.add(map[left]);
        while (left <= right && right <= size - 1) {
            if (maxQueue.peek()[0] - minQueue.peek()[0] <= limit) {
                this.ret = Math.max(this.ret, right - left + 1);
                right++;
                if (right > size - 1) {
                    break;
                }
                map[right] = new int[]{nums[right], right};
                maxQueue.add(map[right]);
                minQueue.add(map[right]);
            } else {
                while (maxQueue.peek()[0] - minQueue.peek()[0] > limit) {
                    maxQueue.remove(map[left]);
                    minQueue.remove(map[left]);
                    left++;
                }
            }
        }
        return this.ret;
    }
}