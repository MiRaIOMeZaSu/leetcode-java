package leetcode.editor._24_swapPairs;

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
class Solution {
    public ListNode swapPairs(ListNode head) {
        // 使用循环
        ListNode pre = head;
        if (head == null) {
            return head;
        }
        ListNode curr = head.next;
        ListNode temp = null;
        if (curr != null) {
            pre.next = null;
            temp = curr.next;
            curr.next = pre;
            head = curr;
        } else {
            return head;
        }
        ListNode lastTail = pre;
        pre = temp;
        lastTail.next = pre;
        while (pre != null) {
            curr = pre.next;
            if (curr != null) {
                temp = curr.next;
                curr.next = pre;
                lastTail.next = curr;
                lastTail = pre;
                pre.next = temp;
                pre = temp;
            } else {
                break;
            }
        }
        return head;
    }
}