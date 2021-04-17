package leetcode.editor._220_containsNearbyAlmostDuplicate;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;


class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 维护小顶堆(以绝对值做比较)
        // 只变换k
        if (nums.length < 1) {
            return false;
        }
        if (k == 0) {
            return t > 0;
        }else if (nums.length == 1) {
            return false;
        }
        Queue<Integer> window = new PriorityQueue<>(Comparator.comparingInt(Math::abs));
        HashSet<Integer> toDel = new HashSet<>();
        window.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int g, l;
            if (Math.abs(window.peek()) > Math.abs(nums[i])) {
                g = Math.abs(window.peek());
                l = Math.abs(nums[i]);
            } else {
                l = Math.abs(window.peek());
                g = Math.abs(nums[i]);
            }
            if (g - l <= t) {
                return true;
            }
            if (i >= k) {
                toDel.add(nums[i - k]);
            }
            while (toDel.contains(window.peek())) {
                toDel.remove(window.peek());
                window.poll();
            }
            window.add(nums[i]);
        }
        return false;
    }
}