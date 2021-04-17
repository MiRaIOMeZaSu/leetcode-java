package leetcode.editor._220_containsNearbyAlmostDuplicate;

import java.util.HashMap;
import java.util.List;

class ListNode {
    int val;
    int count;
    ListNode pre;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        count = 1;
        pre = null;
        next = null;
    }
}

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 维护小顶堆(以绝对值做比较)
        // 只变换k
        if (k == 0 || nums.length <= 1) {
            return false;
        }
        HashMap<Integer, ListNode> map = new HashMap<Integer, ListNode>();
        // 只需要进行一趟插入排序?
        int size = 1;
        ListNode node = new ListNode(nums[0]);
        ListNode head = node;
        map.put(nums[0], node);
        for (int i = 1; i < nums.length; i++) {
            // 进行插入
            if (head.val > nums[i]) {
                node = new ListNode(nums[i]);
                node.next = head;
                head.pre = node;
                head = node;
                map.put(nums[i], node);
            } else {
                ListNode curr = head;
                ListNode pre = head;
                while (curr != null && curr.val < nums[i]) {
                    pre = curr;
                    curr = curr.next;
                }
                if (curr == null) {
                    node = new ListNode(nums[i]);
                    pre.next = node;
                    node.pre = pre;
                    map.put(nums[i], node);
                } else if (curr.val == nums[i]) {
                    node = curr;
                    node.count++;
                } else {
                    // 此时必然是curr.val > nums[i]
                    node = new ListNode(nums[i]);
                    node.next = curr;
                    node.pre = curr.pre;
                    node.next.pre = node;
                    node.pre.next = node;
                    map.put(nums[i], node);
                }
            }
            size++;
            // 进行比较
            if ((node.pre != null && Math.abs((double) node.pre.val - (double) node.val) <= t) || node.count > 1) {
                return true;
            }
            if ((node.next != null && Math.abs((double) node.next.val - (double) node.val) <= t) || node.count > 1) {
                return true;
            }
            // 进行删除
            if (size >= k + 1) {
                node = map.get(nums[i - k]);
                if (map.get(nums[i - k]).count == 1) {
                    map.remove(nums[i - k]);
                    if (node.pre != null) {
                        node.pre.next = node.next;
                    } else {
                        head = node.next;
                    }
                    if (node.next != null) {
                        node.next.pre = node.pre;
                    }
                } else {
                    node.count--;
                }
                size--;
            }
        }
        return false;
    }
}