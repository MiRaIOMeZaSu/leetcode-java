package leetcode.editor.offer._65_add;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public int add(int a, int b) {
        ListNode first = new ListNode(0);
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(2);
        ListNode forth = new ListNode(3);
        first.next = second;
        second.next = third;
        third.next = forth;
        ListNode curr = first;
        int bit = 1;
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((a | bit) == a) {
                curr = curr.next;
            }
            if ((b | bit) == b) {
                curr = curr.next;
            }
            if (curr.val == 2) {
                curr = second;
            } else if (curr.val == 3 || curr.val == 1) {
                if (curr.val == 3) {
                    curr = second;
                }else {
                    curr = first;
                }
                ret |= bit;
            }
            bit = bit << 1;
        }
        return ret;
    }
}