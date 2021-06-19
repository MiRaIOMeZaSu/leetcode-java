package leetcode.editor.offer._62_lastRemaining;


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public int lastRemaining(int n, int m) {
        // 构成一个环链表
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 1; i < n; i++) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        curr.next = head;
        ListNode last = curr;
        curr = head;
        while (curr.next != curr) {
            for (int i = 1; i < m; i++) {
                last = curr;
                curr = curr.next;
            }
            last.next = curr.next;
            curr = last.next;
        }
        return curr.val;
    }
}