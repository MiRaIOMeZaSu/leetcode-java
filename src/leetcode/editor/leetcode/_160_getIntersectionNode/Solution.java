package leetcode.editor._160_getIntersectionNode;

import java.util.LinkedList;
import java.util.List;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        LinkedList<ListNode> linkedListA = new LinkedList<>();
        LinkedList<ListNode> linkedListB = new LinkedList<>();
        ListNode curr = headA;
        while (curr != null) {
            linkedListA.push(curr);
            curr = curr.next;
        }
        curr = headB;
        while (curr != null) {
            linkedListB.push(curr);
            curr = curr.next;
        }
        if (linkedListA.peek() != linkedListB.peek()) {
            return null;
        }
        ListNode last = linkedListA.peek();
        while (!linkedListA.isEmpty()&&!linkedListB.isEmpty()) {
            ListNode tempA = linkedListA.pop();
            ListNode tempB = linkedListB.pop();
            if (tempA != tempB) {
                return last;
            }
            last = tempA;
        }
        return last;
    }
}
