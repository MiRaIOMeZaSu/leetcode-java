package leetcode.editor._206_reverseList;

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
    public ListNode reverseList(ListNode head) {
        // 使用循环
        if (head == null) {
            return head;
        }
        ListNode pre = head;
        ListNode ret = head;
        ListNode curr = head.next;
        if (curr != null) {
            while (curr.next != null) {
                ListNode temp = curr.next;
                curr.next = pre;
                pre = curr;
                curr = temp;
            }
            curr.next = pre;
            ret = curr;
        }
        head.next = null;
        return ret;
    }
}