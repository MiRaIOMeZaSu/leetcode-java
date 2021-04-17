package leetcode.editor._220_containsNearbyAlmostDuplicate;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

class MyInteger {
    MyInteger(int val, int index) {
        this.val = val;
        this.index = index;
    }

    public int val;
    public int index;
}

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 维护小顶堆(以绝对值做比较)
        // 只变换k
        if (k == 0 || nums.length <= 1) {
            return false;
        }
        Queue<MyInteger> greater = new PriorityQueue<>((o1, o2) -> {
            if (o1.val != o2.val) {
                return o1.val - o2.val;
            } else {
                return o1.index - o2.index;
            }
        });
        Queue<MyInteger> less = new PriorityQueue<>((o1, o2) -> {
            if (o1.val != o2.val) {
                return o2.val - o1.val;
            } else {
                return o1.index - o2.index;
            }
        });
        HashSet<Integer> toDel = new HashSet<>();
        // 只往后面看,因为往后走时更后面还会往回比较
        // nums长度必然大于2
        for (int i = 1; i < nums.length; i++) {
            // 此处做移动操作
            while (!greater.isEmpty() && greater.peek().val < nums[i]) {
                less.add(greater.poll());
            }
            while (!less.isEmpty() && less.peek().val > nums[i]) {
                greater.add(less.poll());
            }
            if (nums[i - 1] < nums[i]) {
                less.add(new MyInteger(nums[i - 1], i - 1));
            } else {
                greater.add(new MyInteger(nums[i - 1], i - 1));
            }
            while (!greater.isEmpty() && toDel.contains(greater.peek().val)) {
                toDel.remove(greater.peek().val);
                greater.poll();
            }
            while (!less.isEmpty() && toDel.contains(less.peek().val)) {
                toDel.remove(less.peek().val);
                less.poll();
            }
            if (!greater.isEmpty() && Math.abs((double) greater.peek().val - (double)nums[i]) <= (double)t) {
                return true;
            }
            if (!less.isEmpty() && Math.abs((double)less.peek().val - (double)nums[i]) <= (double)t) {
                return true;
            }
            if (i >= k) {
                toDel.add(nums[i - k]);
            }
        }
        return false;
    }
}