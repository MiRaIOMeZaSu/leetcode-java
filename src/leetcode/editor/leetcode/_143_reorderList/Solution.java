package leetcode.editor._143_reorderList;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public void reorderList(ListNode head) {
        // 先尝试使用数组存储的方法?
        // 将结点链按指定方式重排列?
        // 说白了是两个指针× -> 需要用栈存储从后到前的位置
        List<ListNode> listNodes = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            listNodes.add(curr);
            curr = curr.next;
        }
        // 两个指针
        int left = 1;
        int right = listNodes.size() - 1;
        curr = listNodes.get(0);
        while (left <= right) {
            if (left <= right) {
                curr.next = listNodes.get(right);
                curr = curr.next;
                right--;
            }
            if (left <= right) {
                curr.next = listNodes.get(left);
                curr = curr.next;
                left++;
            }
        }
        curr.next = null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        ListNode _2 = new ListNode(2);
        ListNode _3 = new ListNode(3);
        ListNode _4 = new ListNode(4);
        head.next = _2;
        _2.next = _3;
        _3.next = _4;
        solution.reorderList(head);
    }
}