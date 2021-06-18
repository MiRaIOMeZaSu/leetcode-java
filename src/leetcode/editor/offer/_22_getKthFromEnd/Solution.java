package leetcode.editor.offer._22_getKthFromEnd;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode curr = head;
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }
        ListNode ret = null;
        for (int i = 0; i < k; i++) {
            if (stack.isEmpty()) {
                return null;
            }
            ret = stack.pop();
        }
        return ret;
    }
}